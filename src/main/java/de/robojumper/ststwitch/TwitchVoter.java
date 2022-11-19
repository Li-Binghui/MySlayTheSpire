/*     */ package de.robojumper.ststwitch;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.localization.UIStrings;
/*     */ import com.megacrit.cardcrawl.random.Random;
/*     */ import java.util.HashSet;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import java.util.function.Consumer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TwitchVoter
/*     */   implements TwitchMessageListener
/*     */ {
/*  21 */   private static List<TwitchVoteListener> listeners = new LinkedList<>();
/*     */ 
/*     */   
/*  24 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("TwitchVoter");
/*  25 */   public static final String[] TEXT = uiStrings.TEXT;
/*     */   
/*     */   private static boolean wasConnected = false;
/*     */   
/*     */   private boolean isVoting;
/*     */   
/*     */   private Consumer<Integer> voteCb;
/*     */   
/*     */   private float timer;
/*     */   
/*     */   private boolean triggered = false;
/*     */   
/*     */   private TwitchVoteOption[] options;
/*  38 */   private Set<String> votedUsernames = new HashSet<>();
/*     */   private TwitchConfig twitchConfig;
/*     */   
/*     */   TwitchVoter(TwitchConfig twitchConfig) {
/*  42 */     this.twitchConfig = twitchConfig;
/*  43 */     this.isVoting = false;
/*  44 */     TwitchPanel.getTwitch().registerMessageListener(this);
/*     */   }
/*     */   
/*     */   public void update() {
/*  48 */     if (isCurrentlyVoting()) {
/*  49 */       this.timer -= Gdx.graphics.getDeltaTime();
/*  50 */       if (this.timer <= 0.0F && !this.triggered) {
/*  51 */         completeVoting();
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/*  56 */     boolean newStatus = (TwitchPanel.getTwitch().getConnectionStatus() == TwitchConnection.ConnectionStatus.CONNECTED);
/*  57 */     if (wasConnected != newStatus) {
/*  58 */       if (newStatus) {
/*  59 */         notifyListenersConnected();
/*     */       } else {
/*  61 */         notifyListenersDisconnected();
/*     */       } 
/*     */     }
/*  64 */     wasConnected = newStatus;
/*     */   }
/*     */   
/*     */   private void notifyComplete() {
/*  68 */     int maxIdx = 0;
/*  69 */     for (int i = 1; i < this.options.length; i++) {
/*     */       
/*  71 */       if ((this.options[i]).voteCount != 0 && (this.options[i]).voteCount == (this.options[maxIdx]).voteCount) {
/*     */         
/*  73 */         if ((new Random()).randomBoolean()) {
/*  74 */           maxIdx = i;
/*     */         }
/*  76 */       } else if ((this.options[i]).voteCount > (this.options[maxIdx]).voteCount) {
/*  77 */         maxIdx = i;
/*     */       } 
/*     */     } 
/*  80 */     this.voteCb.accept(Integer.valueOf(maxIdx));
/*     */   }
/*     */   
/*     */   private void notifyListenersConnected() {
/*  84 */     for (TwitchVoteListener l : listeners) {
/*  85 */       l.onTwitchAvailable();
/*     */     }
/*     */   }
/*     */   
/*     */   private void notifyListenersDisconnected() {
/*  90 */     for (TwitchVoteListener l : listeners) {
/*  91 */       l.onTwitchUnavailable();
/*     */     }
/*     */   }
/*     */   
/*     */   public static void registerListener(TwitchVoteListener L) {
/*  96 */     listeners.add(L);
/*     */   }
/*     */   
/*     */   private boolean isCurrentlyVoting() {
/* 100 */     return this.isVoting;
/*     */   }
/*     */   
/*     */   private boolean isVotingAvailable() {
/* 104 */     return (!this.isVoting && TwitchPanel.getTwitch().getConnectionStatus() == TwitchConnection.ConnectionStatus.CONNECTED);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVotingConnected() {
/* 109 */     return (TwitchPanel.getTwitch().getConnectionStatus() == TwitchConnection.ConnectionStatus.CONNECTED);
/*     */   }
/*     */   
/*     */   public TwitchVoteOption[] getOptions() {
/* 113 */     return this.options;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean initiateSimpleNumberVote(String[] strOptions, Consumer<Integer> voteCb) {
/* 124 */     TwitchVoteOption[] options = new TwitchVoteOption[strOptions.length];
/* 125 */     for (int i = 0; i < strOptions.length; i++)
/*     */     {
/* 127 */       options[i] = new TwitchVoteOption(strOptions[i], "#" + i, "^#" + i + ".*$");
/*     */     }
/* 129 */     return initiateVote(options, voteCb);
/*     */   }
/*     */   
/*     */   private boolean initiateVote(TwitchVoteOption[] options, Consumer<Integer> voteCb) {
/* 133 */     if (isVotingAvailable()) {
/* 134 */       this.timer = this.twitchConfig.getTimer();
/* 135 */       this.triggered = false;
/* 136 */       this.isVoting = true;
/* 137 */       this.voteCb = voteCb;
/* 138 */       this.options = TwitchVoteOption.cloneArrayOfOptions(options);
/* 139 */       this.votedUsernames.clear();
/* 140 */       publishOptions();
/* 141 */       return true;
/*     */     } 
/* 143 */     return false;
/*     */   }
/*     */   
/*     */   private void publishOptions() {
/* 147 */     StringBuilder sb = new StringBuilder();
/* 148 */     sb.append(TEXT[0]);
/* 149 */     for (int i = 0; i < this.options.length; i++) {
/* 150 */       sb.append((this.options[i]).commandHint);
/* 151 */       sb.append(": ");
/* 152 */       sb.append((this.options[i]).displayName);
/* 153 */       if (i != this.options.length - 1) {
/* 154 */         sb.append("; ");
/*     */       }
/*     */     } 
/* 157 */     TwitchPanel.getTwitch().sendMessage(sb.toString());
/*     */   }
/*     */ 
/*     */   
/*     */   public void onMessage(String msg, String user) {
/* 162 */     for (TwitchVoteOption option : this.options) {
/* 163 */       if (option.matchPattern.matcher(msg).matches() && !this.votedUsernames.contains(user)) {
/* 164 */         option.voteCount++;
/* 165 */         this.votedUsernames.add(user);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getSecondsRemaining() {
/* 171 */     return (int)this.timer;
/*     */   }
/*     */ 
/*     */   
/*     */   public void endVoting(boolean canceled) {
/* 176 */     if (isCurrentlyVoting()) {
/* 177 */       this.isVoting = false;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void completeVoting() {
/* 184 */     notifyComplete();
/* 185 */     endVoting(false);
/* 186 */     this.triggered = true;
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\de\robojumper\ststwitch\TwitchVoter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */