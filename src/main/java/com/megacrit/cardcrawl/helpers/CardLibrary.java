package com.megacrit.cardcrawl.helpers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.blue.Aggregate;
import com.megacrit.cardcrawl.cards.blue.AllForOne;
import com.megacrit.cardcrawl.cards.blue.Amplify;
import com.megacrit.cardcrawl.cards.blue.AutoShields;
import com.megacrit.cardcrawl.cards.blue.BallLightning;
import com.megacrit.cardcrawl.cards.blue.Barrage;
import com.megacrit.cardcrawl.cards.blue.BeamCell;
import com.megacrit.cardcrawl.cards.blue.BiasedCognition;
import com.megacrit.cardcrawl.cards.blue.Blizzard;
import com.megacrit.cardcrawl.cards.blue.BootSequence;
import com.megacrit.cardcrawl.cards.blue.Buffer;
import com.megacrit.cardcrawl.cards.blue.Capacitor;
import com.megacrit.cardcrawl.cards.blue.Chaos;
import com.megacrit.cardcrawl.cards.blue.Chill;
import com.megacrit.cardcrawl.cards.blue.Claw;
import com.megacrit.cardcrawl.cards.blue.ColdSnap;
import com.megacrit.cardcrawl.cards.blue.CompileDriver;
import com.megacrit.cardcrawl.cards.blue.ConserveBattery;
import com.megacrit.cardcrawl.cards.blue.Consume;
import com.megacrit.cardcrawl.cards.blue.Coolheaded;
import com.megacrit.cardcrawl.cards.blue.CoreSurge;
import com.megacrit.cardcrawl.cards.blue.CreativeAI;
import com.megacrit.cardcrawl.cards.blue.Darkness;
import com.megacrit.cardcrawl.cards.blue.Defend_Blue;
import com.megacrit.cardcrawl.cards.blue.Defragment;
import com.megacrit.cardcrawl.cards.blue.DoomAndGloom;
import com.megacrit.cardcrawl.cards.blue.DoubleEnergy;
import com.megacrit.cardcrawl.cards.blue.Dualcast;
import com.megacrit.cardcrawl.cards.blue.EchoForm;
import com.megacrit.cardcrawl.cards.blue.Electrodynamics;
import com.megacrit.cardcrawl.cards.blue.Equilibrium;
import com.megacrit.cardcrawl.cards.blue.FTL;
import com.megacrit.cardcrawl.cards.blue.Fission;
import com.megacrit.cardcrawl.cards.blue.ForceField;
import com.megacrit.cardcrawl.cards.blue.Fusion;
import com.megacrit.cardcrawl.cards.blue.GeneticAlgorithm;
import com.megacrit.cardcrawl.cards.blue.Glacier;
import com.megacrit.cardcrawl.cards.blue.GoForTheEyes;
import com.megacrit.cardcrawl.cards.blue.Heatsinks;
import com.megacrit.cardcrawl.cards.blue.HelloWorld;
import com.megacrit.cardcrawl.cards.blue.Hologram;
import com.megacrit.cardcrawl.cards.blue.Hyperbeam;
import com.megacrit.cardcrawl.cards.blue.Leap;
import com.megacrit.cardcrawl.cards.blue.LockOn;
import com.megacrit.cardcrawl.cards.blue.Loop;
import com.megacrit.cardcrawl.cards.blue.MachineLearning;
import com.megacrit.cardcrawl.cards.blue.Melter;
import com.megacrit.cardcrawl.cards.blue.MeteorStrike;
import com.megacrit.cardcrawl.cards.blue.MultiCast;
import com.megacrit.cardcrawl.cards.blue.Overclock;
import com.megacrit.cardcrawl.cards.blue.Rainbow;
import com.megacrit.cardcrawl.cards.blue.Reboot;
import com.megacrit.cardcrawl.cards.blue.Rebound;
import com.megacrit.cardcrawl.cards.blue.Recursion;
import com.megacrit.cardcrawl.cards.blue.Recycle;
import com.megacrit.cardcrawl.cards.blue.ReinforcedBody;
import com.megacrit.cardcrawl.cards.blue.Reprogram;
import com.megacrit.cardcrawl.cards.blue.RipAndTear;
import com.megacrit.cardcrawl.cards.blue.Scrape;
import com.megacrit.cardcrawl.cards.blue.Seek;
import com.megacrit.cardcrawl.cards.blue.SelfRepair;
import com.megacrit.cardcrawl.cards.blue.Skim;
import com.megacrit.cardcrawl.cards.blue.Stack;
import com.megacrit.cardcrawl.cards.blue.StaticDischarge;
import com.megacrit.cardcrawl.cards.blue.SteamBarrier;
import com.megacrit.cardcrawl.cards.blue.Storm;
import com.megacrit.cardcrawl.cards.blue.Streamline;
import com.megacrit.cardcrawl.cards.blue.Strike_Blue;
import com.megacrit.cardcrawl.cards.blue.Sunder;
import com.megacrit.cardcrawl.cards.blue.SweepingBeam;
import com.megacrit.cardcrawl.cards.blue.Tempest;
import com.megacrit.cardcrawl.cards.blue.ThunderStrike;
import com.megacrit.cardcrawl.cards.blue.Turbo;
import com.megacrit.cardcrawl.cards.blue.WhiteNoise;
import com.megacrit.cardcrawl.cards.blue.Zap;
import com.megacrit.cardcrawl.cards.colorless.Apotheosis;
import com.megacrit.cardcrawl.cards.colorless.Apparition;
import com.megacrit.cardcrawl.cards.colorless.BandageUp;
import com.megacrit.cardcrawl.cards.colorless.Bite;
import com.megacrit.cardcrawl.cards.colorless.Blind;
import com.megacrit.cardcrawl.cards.colorless.Chrysalis;
import com.megacrit.cardcrawl.cards.colorless.DarkShackles;
import com.megacrit.cardcrawl.cards.colorless.DeepBreath;
import com.megacrit.cardcrawl.cards.colorless.Discovery;
import com.megacrit.cardcrawl.cards.colorless.DramaticEntrance;
import com.megacrit.cardcrawl.cards.colorless.Enlightenment;
import com.megacrit.cardcrawl.cards.colorless.Finesse;
import com.megacrit.cardcrawl.cards.colorless.FlashOfSteel;
import com.megacrit.cardcrawl.cards.colorless.Forethought;
import com.megacrit.cardcrawl.cards.colorless.GoodInstincts;
import com.megacrit.cardcrawl.cards.colorless.HandOfGreed;
import com.megacrit.cardcrawl.cards.colorless.Impatience;
import com.megacrit.cardcrawl.cards.colorless.JAX;
import com.megacrit.cardcrawl.cards.colorless.JackOfAllTrades;
import com.megacrit.cardcrawl.cards.colorless.Madness;
import com.megacrit.cardcrawl.cards.colorless.Magnetism;
import com.megacrit.cardcrawl.cards.colorless.MasterOfStrategy;
import com.megacrit.cardcrawl.cards.colorless.Mayhem;
import com.megacrit.cardcrawl.cards.colorless.Metamorphosis;
import com.megacrit.cardcrawl.cards.colorless.MindBlast;
import com.megacrit.cardcrawl.cards.colorless.Panacea;
import com.megacrit.cardcrawl.cards.colorless.Panache;
import com.megacrit.cardcrawl.cards.colorless.PanicButton;
import com.megacrit.cardcrawl.cards.colorless.Purity;
import com.megacrit.cardcrawl.cards.colorless.RitualDagger;
import com.megacrit.cardcrawl.cards.colorless.SadisticNature;
import com.megacrit.cardcrawl.cards.colorless.SecretTechnique;
import com.megacrit.cardcrawl.cards.colorless.SecretWeapon;
import com.megacrit.cardcrawl.cards.colorless.SwiftStrike;
import com.megacrit.cardcrawl.cards.colorless.TheBomb;
import com.megacrit.cardcrawl.cards.colorless.ThinkingAhead;
import com.megacrit.cardcrawl.cards.colorless.Transmutation;
import com.megacrit.cardcrawl.cards.colorless.Trip;
import com.megacrit.cardcrawl.cards.colorless.Violence;
import com.megacrit.cardcrawl.cards.curses.AscendersBane;
import com.megacrit.cardcrawl.cards.curses.Clumsy;
import com.megacrit.cardcrawl.cards.curses.CurseOfTheBell;
import com.megacrit.cardcrawl.cards.curses.Decay;
import com.megacrit.cardcrawl.cards.curses.Doubt;
import com.megacrit.cardcrawl.cards.curses.Injury;
import com.megacrit.cardcrawl.cards.curses.Necronomicurse;
import com.megacrit.cardcrawl.cards.curses.Normality;
import com.megacrit.cardcrawl.cards.curses.Pain;
import com.megacrit.cardcrawl.cards.curses.Parasite;
import com.megacrit.cardcrawl.cards.curses.Pride;
import com.megacrit.cardcrawl.cards.curses.Regret;
import com.megacrit.cardcrawl.cards.curses.Shame;
import com.megacrit.cardcrawl.cards.curses.Writhe;
import com.megacrit.cardcrawl.cards.green.AThousandCuts;
import com.megacrit.cardcrawl.cards.green.Accuracy;
import com.megacrit.cardcrawl.cards.green.Acrobatics;
import com.megacrit.cardcrawl.cards.green.Adrenaline;
import com.megacrit.cardcrawl.cards.green.AfterImage;
import com.megacrit.cardcrawl.cards.green.Alchemize;
import com.megacrit.cardcrawl.cards.green.AllOutAttack;
import com.megacrit.cardcrawl.cards.green.Backflip;
import com.megacrit.cardcrawl.cards.green.Backstab;
import com.megacrit.cardcrawl.cards.green.Bane;
import com.megacrit.cardcrawl.cards.green.BladeDance;
import com.megacrit.cardcrawl.cards.green.Blur;
import com.megacrit.cardcrawl.cards.green.BouncingFlask;
import com.megacrit.cardcrawl.cards.green.BulletTime;
import com.megacrit.cardcrawl.cards.green.Burst;
import com.megacrit.cardcrawl.cards.green.CalculatedGamble;
import com.megacrit.cardcrawl.cards.green.Caltrops;
import com.megacrit.cardcrawl.cards.green.Catalyst;
import com.megacrit.cardcrawl.cards.green.Choke;
import com.megacrit.cardcrawl.cards.green.CloakAndDagger;
import com.megacrit.cardcrawl.cards.green.Concentrate;
import com.megacrit.cardcrawl.cards.green.CorpseExplosion;
import com.megacrit.cardcrawl.cards.green.CripplingPoison;
import com.megacrit.cardcrawl.cards.green.DaggerSpray;
import com.megacrit.cardcrawl.cards.green.DaggerThrow;
import com.megacrit.cardcrawl.cards.green.Dash;
import com.megacrit.cardcrawl.cards.green.DeadlyPoison;
import com.megacrit.cardcrawl.cards.green.Defend_Green;
import com.megacrit.cardcrawl.cards.green.Deflect;
import com.megacrit.cardcrawl.cards.green.DieDieDie;
import com.megacrit.cardcrawl.cards.green.Distraction;
import com.megacrit.cardcrawl.cards.green.DodgeAndRoll;
import com.megacrit.cardcrawl.cards.green.Doppelganger;
import com.megacrit.cardcrawl.cards.green.EndlessAgony;
import com.megacrit.cardcrawl.cards.green.Envenom;
import com.megacrit.cardcrawl.cards.green.EscapePlan;
import com.megacrit.cardcrawl.cards.green.Eviscerate;
import com.megacrit.cardcrawl.cards.green.Expertise;
import com.megacrit.cardcrawl.cards.green.Finisher;
import com.megacrit.cardcrawl.cards.green.Flechettes;
import com.megacrit.cardcrawl.cards.green.FlyingKnee;
import com.megacrit.cardcrawl.cards.green.Footwork;
import com.megacrit.cardcrawl.cards.green.GlassKnife;
import com.megacrit.cardcrawl.cards.green.GrandFinale;
import com.megacrit.cardcrawl.cards.green.HeelHook;
import com.megacrit.cardcrawl.cards.green.InfiniteBlades;
import com.megacrit.cardcrawl.cards.green.LegSweep;
import com.megacrit.cardcrawl.cards.green.Malaise;
import com.megacrit.cardcrawl.cards.green.MasterfulStab;
import com.megacrit.cardcrawl.cards.green.Neutralize;
import com.megacrit.cardcrawl.cards.green.Nightmare;
import com.megacrit.cardcrawl.cards.green.NoxiousFumes;
import com.megacrit.cardcrawl.cards.green.Outmaneuver;
import com.megacrit.cardcrawl.cards.green.PhantasmalKiller;
import com.megacrit.cardcrawl.cards.green.PiercingWail;
import com.megacrit.cardcrawl.cards.green.PoisonedStab;
import com.megacrit.cardcrawl.cards.green.Predator;
import com.megacrit.cardcrawl.cards.green.Prepared;
import com.megacrit.cardcrawl.cards.green.QuickSlash;
import com.megacrit.cardcrawl.cards.green.Reflex;
import com.megacrit.cardcrawl.cards.green.RiddleWithHoles;
import com.megacrit.cardcrawl.cards.green.Setup;
import com.megacrit.cardcrawl.cards.green.Skewer;
import com.megacrit.cardcrawl.cards.green.Slice;
import com.megacrit.cardcrawl.cards.green.SneakyStrike;
import com.megacrit.cardcrawl.cards.green.StormOfSteel;
import com.megacrit.cardcrawl.cards.green.Strike_Green;
import com.megacrit.cardcrawl.cards.green.SuckerPunch;
import com.megacrit.cardcrawl.cards.green.Survivor;
import com.megacrit.cardcrawl.cards.green.Tactician;
import com.megacrit.cardcrawl.cards.green.Terror;
import com.megacrit.cardcrawl.cards.green.ToolsOfTheTrade;
import com.megacrit.cardcrawl.cards.green.Unload;
import com.megacrit.cardcrawl.cards.green.WellLaidPlans;
import com.megacrit.cardcrawl.cards.green.WraithForm;
import com.megacrit.cardcrawl.cards.optionCards.BecomeAlmighty;
import com.megacrit.cardcrawl.cards.optionCards.FameAndFortune;
import com.megacrit.cardcrawl.cards.optionCards.LiveForever;
import com.megacrit.cardcrawl.cards.purple.Alpha;
import com.megacrit.cardcrawl.cards.purple.BattleHymn;
import com.megacrit.cardcrawl.cards.purple.Blasphemy;
import com.megacrit.cardcrawl.cards.purple.BowlingBash;
import com.megacrit.cardcrawl.cards.purple.Brilliance;
import com.megacrit.cardcrawl.cards.purple.CarveReality;
import com.megacrit.cardcrawl.cards.purple.Collect;
import com.megacrit.cardcrawl.cards.purple.Conclude;
import com.megacrit.cardcrawl.cards.purple.ConjureBlade;
import com.megacrit.cardcrawl.cards.purple.Consecrate;
import com.megacrit.cardcrawl.cards.purple.Crescendo;
import com.megacrit.cardcrawl.cards.purple.CrushJoints;
import com.megacrit.cardcrawl.cards.purple.CutThroughFate;
import com.megacrit.cardcrawl.cards.purple.DeceiveReality;
import com.megacrit.cardcrawl.cards.purple.Defend_Watcher;
import com.megacrit.cardcrawl.cards.purple.DeusExMachina;
import com.megacrit.cardcrawl.cards.purple.DevaForm;
import com.megacrit.cardcrawl.cards.purple.Devotion;
import com.megacrit.cardcrawl.cards.purple.EmptyBody;
import com.megacrit.cardcrawl.cards.purple.EmptyFist;
import com.megacrit.cardcrawl.cards.purple.EmptyMind;
import com.megacrit.cardcrawl.cards.purple.Eruption;
import com.megacrit.cardcrawl.cards.purple.Establishment;
import com.megacrit.cardcrawl.cards.purple.Evaluate;
import com.megacrit.cardcrawl.cards.purple.Fasting;
import com.megacrit.cardcrawl.cards.purple.FearNoEvil;
import com.megacrit.cardcrawl.cards.purple.FlurryOfBlows;
import com.megacrit.cardcrawl.cards.purple.FlyingSleeves;
import com.megacrit.cardcrawl.cards.purple.FollowUp;
import com.megacrit.cardcrawl.cards.purple.ForeignInfluence;
import com.megacrit.cardcrawl.cards.purple.Foresight;
import com.megacrit.cardcrawl.cards.purple.Halt;
import com.megacrit.cardcrawl.cards.purple.Indignation;
import com.megacrit.cardcrawl.cards.purple.InnerPeace;
import com.megacrit.cardcrawl.cards.purple.Judgement;
import com.megacrit.cardcrawl.cards.purple.JustLucky;
import com.megacrit.cardcrawl.cards.purple.LessonLearned;
import com.megacrit.cardcrawl.cards.purple.LikeWater;
import com.megacrit.cardcrawl.cards.purple.MasterReality;
import com.megacrit.cardcrawl.cards.purple.Meditate;
import com.megacrit.cardcrawl.cards.purple.MentalFortress;
import com.megacrit.cardcrawl.cards.purple.Nirvana;
import com.megacrit.cardcrawl.cards.purple.Omniscience;
import com.megacrit.cardcrawl.cards.purple.Perseverance;
import com.megacrit.cardcrawl.cards.purple.Pray;
import com.megacrit.cardcrawl.cards.purple.PressurePoints;
import com.megacrit.cardcrawl.cards.purple.Prostrate;
import com.megacrit.cardcrawl.cards.purple.Protect;
import com.megacrit.cardcrawl.cards.purple.Ragnarok;
import com.megacrit.cardcrawl.cards.purple.ReachHeaven;
import com.megacrit.cardcrawl.cards.purple.Rushdown;
import com.megacrit.cardcrawl.cards.purple.Sanctity;
import com.megacrit.cardcrawl.cards.purple.SandsOfTime;
import com.megacrit.cardcrawl.cards.purple.SashWhip;
import com.megacrit.cardcrawl.cards.purple.Scrawl;
import com.megacrit.cardcrawl.cards.purple.SignatureMove;
import com.megacrit.cardcrawl.cards.purple.SimmeringFury;
import com.megacrit.cardcrawl.cards.purple.SpiritShield;
import com.megacrit.cardcrawl.cards.purple.Strike_Purple;
import com.megacrit.cardcrawl.cards.purple.Study;
import com.megacrit.cardcrawl.cards.purple.Swivel;
import com.megacrit.cardcrawl.cards.purple.TalkToTheHand;
import com.megacrit.cardcrawl.cards.purple.Tantrum;
import com.megacrit.cardcrawl.cards.purple.ThirdEye;
import com.megacrit.cardcrawl.cards.purple.Tranquility;
import com.megacrit.cardcrawl.cards.purple.Vault;
import com.megacrit.cardcrawl.cards.purple.Vigilance;
import com.megacrit.cardcrawl.cards.purple.Wallop;
import com.megacrit.cardcrawl.cards.purple.WaveOfTheHand;
import com.megacrit.cardcrawl.cards.purple.Weave;
import com.megacrit.cardcrawl.cards.purple.WheelKick;
import com.megacrit.cardcrawl.cards.purple.WindmillStrike;
import com.megacrit.cardcrawl.cards.purple.Wish;
import com.megacrit.cardcrawl.cards.purple.Worship;
import com.megacrit.cardcrawl.cards.purple.WreathOfFlame;
import com.megacrit.cardcrawl.cards.red.Anger;
import com.megacrit.cardcrawl.cards.red.Armaments;
import com.megacrit.cardcrawl.cards.red.Barricade;
import com.megacrit.cardcrawl.cards.red.Bash;
import com.megacrit.cardcrawl.cards.red.BattleTrance;
import com.megacrit.cardcrawl.cards.red.Berserk;
import com.megacrit.cardcrawl.cards.red.BloodForBlood;
import com.megacrit.cardcrawl.cards.red.Bloodletting;
import com.megacrit.cardcrawl.cards.red.Bludgeon;
import com.megacrit.cardcrawl.cards.red.BodySlam;
import com.megacrit.cardcrawl.cards.red.Brutality;
import com.megacrit.cardcrawl.cards.red.BurningPact;
import com.megacrit.cardcrawl.cards.red.Carnage;
import com.megacrit.cardcrawl.cards.red.Clash;
import com.megacrit.cardcrawl.cards.red.Cleave;
import com.megacrit.cardcrawl.cards.red.Clothesline;
import com.megacrit.cardcrawl.cards.red.Combust;
import com.megacrit.cardcrawl.cards.red.Corruption;
import com.megacrit.cardcrawl.cards.red.DarkEmbrace;
import com.megacrit.cardcrawl.cards.red.Defend_Red;
import com.megacrit.cardcrawl.cards.red.DemonForm;
import com.megacrit.cardcrawl.cards.red.Disarm;
import com.megacrit.cardcrawl.cards.red.DoubleTap;
import com.megacrit.cardcrawl.cards.red.Dropkick;
import com.megacrit.cardcrawl.cards.red.DualWield;
import com.megacrit.cardcrawl.cards.red.Entrench;
import com.megacrit.cardcrawl.cards.red.Evolve;
import com.megacrit.cardcrawl.cards.red.Exhume;
import com.megacrit.cardcrawl.cards.red.Feed;
import com.megacrit.cardcrawl.cards.red.FeelNoPain;
import com.megacrit.cardcrawl.cards.red.FiendFire;
import com.megacrit.cardcrawl.cards.red.FireBreathing;
import com.megacrit.cardcrawl.cards.red.FlameBarrier;
import com.megacrit.cardcrawl.cards.red.Flex;
import com.megacrit.cardcrawl.cards.red.GhostlyArmor;
import com.megacrit.cardcrawl.cards.red.Havoc;
import com.megacrit.cardcrawl.cards.red.Headbutt;
import com.megacrit.cardcrawl.cards.red.HeavyBlade;
import com.megacrit.cardcrawl.cards.red.Hemokinesis;
import com.megacrit.cardcrawl.cards.red.Immolate;
import com.megacrit.cardcrawl.cards.red.Impervious;
import com.megacrit.cardcrawl.cards.red.InfernalBlade;
import com.megacrit.cardcrawl.cards.red.Inflame;
import com.megacrit.cardcrawl.cards.red.Intimidate;
import com.megacrit.cardcrawl.cards.red.IronWave;
import com.megacrit.cardcrawl.cards.red.Juggernaut;
import com.megacrit.cardcrawl.cards.red.LimitBreak;
import com.megacrit.cardcrawl.cards.red.Metallicize;
import com.megacrit.cardcrawl.cards.red.Offering;
import com.megacrit.cardcrawl.cards.red.PerfectedStrike;
import com.megacrit.cardcrawl.cards.red.PommelStrike;
import com.megacrit.cardcrawl.cards.red.PowerThrough;
import com.megacrit.cardcrawl.cards.red.Pummel;
import com.megacrit.cardcrawl.cards.red.Rage;
import com.megacrit.cardcrawl.cards.red.Rampage;
import com.megacrit.cardcrawl.cards.red.Reaper;
import com.megacrit.cardcrawl.cards.red.RecklessCharge;
import com.megacrit.cardcrawl.cards.red.Rupture;
import com.megacrit.cardcrawl.cards.red.SearingBlow;
import com.megacrit.cardcrawl.cards.red.SecondWind;
import com.megacrit.cardcrawl.cards.red.SeeingRed;
import com.megacrit.cardcrawl.cards.red.Sentinel;
import com.megacrit.cardcrawl.cards.red.SeverSoul;
import com.megacrit.cardcrawl.cards.red.Shockwave;
import com.megacrit.cardcrawl.cards.red.ShrugItOff;
import com.megacrit.cardcrawl.cards.red.SpotWeakness;
import com.megacrit.cardcrawl.cards.red.Strike_Red;
import com.megacrit.cardcrawl.cards.red.SwordBoomerang;
import com.megacrit.cardcrawl.cards.red.ThunderClap;
import com.megacrit.cardcrawl.cards.red.TrueGrit;
import com.megacrit.cardcrawl.cards.red.TwinStrike;
import com.megacrit.cardcrawl.cards.red.Uppercut;
import com.megacrit.cardcrawl.cards.red.Warcry;
import com.megacrit.cardcrawl.cards.red.Whirlwind;
import com.megacrit.cardcrawl.cards.red.WildStrike;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.cards.status.Slimed;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.cards.status.Wound;
import com.megacrit.cardcrawl.cards.tempCards.Beta;
import com.megacrit.cardcrawl.cards.tempCards.Expunger;
import com.megacrit.cardcrawl.cards.tempCards.Insight;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.cards.tempCards.Omega;
import com.megacrit.cardcrawl.cards.tempCards.Safety;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.cards.tempCards.Smite;
import com.megacrit.cardcrawl.cards.tempCards.ThroughViolence;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.metrics.BotDataUploader;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/helpers/CardLibrary.class */
public class CardLibrary {
    private static final Logger logger = LogManager.getLogger(CardLibrary.class.getName());
    public static int totalCardCount = 0;
    public static HashMap<String, AbstractCard> cards = new HashMap<>();
    private static HashMap<String, AbstractCard> curses = new HashMap<>();
    public static int redCards = 0;
    public static int greenCards = 0;
    public static int blueCards = 0;
    public static int purpleCards = 0;
    public static int colorlessCards = 0;
    public static int curseCards = 0;
    public static int seenRedCards = 0;
    public static int seenGreenCards = 0;
    public static int seenBlueCards = 0;
    public static int seenPurpleCards = 0;
    public static int seenColorlessCards = 0;
    public static int seenCurseCards = 0;

    /* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/helpers/CardLibrary$LibraryType.class */
    public enum LibraryType {
        RED,
        GREEN,
        BLUE,
        PURPLE,
        CURSE,
        COLORLESS
    }

    public static void initialize() {
        long startTime = System.currentTimeMillis();
        addRedCards();
        addGreenCards();
        addBlueCards();
        addPurpleCards();
        addColorlessCards();
        addCurseCards();
        if (Settings.isDev) {
        }
        logger.info("Card load time: " + (System.currentTimeMillis() - startTime) + "ms with " + cards.size() + " cards");
        if (Settings.isDev) {
            logger.info("[INFO] Red Cards: \t" + redCards);
            logger.info("[INFO] Green Cards: \t" + greenCards);
            logger.info("[INFO] Blue Cards: \t" + blueCards);
            logger.info("[INFO] Purple Cards: \t" + purpleCards);
            logger.info("[INFO] Colorless Cards: \t" + colorlessCards);
            logger.info("[INFO] Curse Cards: \t" + curseCards);
            logger.info("[INFO] Total Cards: \t" + (redCards + greenCards + blueCards + purpleCards + colorlessCards + curseCards));
        }
    }

    public static void resetForReload() {
        cards = new HashMap<>();
        curses = new HashMap<>();
        totalCardCount = 0;
        redCards = 0;
        greenCards = 0;
        blueCards = 0;
        purpleCards = 0;
        colorlessCards = 0;
        curseCards = 0;
        seenRedCards = 0;
        seenGreenCards = 0;
        seenBlueCards = 0;
        seenPurpleCards = 0;
        seenColorlessCards = 0;
        seenCurseCards = 0;
    }

    private static void addRedCards() {
        add(new Anger());
        add(new Armaments());
        add(new Barricade());
        add(new Bash());
        add(new BattleTrance());
        add(new Berserk());
        add(new BloodForBlood());
        add(new Bloodletting());
        add(new Bludgeon());
        add(new BodySlam());
        add(new Brutality());
        add(new BurningPact());
        add(new Carnage());
        add(new Clash());
        add(new Cleave());
        add(new Clothesline());
        add(new Combust());
        add(new Corruption());
        add(new DarkEmbrace());
        add(new Defend_Red());
        add(new DemonForm());
        add(new Disarm());
        add(new DoubleTap());
        add(new Dropkick());
        add(new DualWield());
        add(new Entrench());
        add(new Evolve());
        add(new Exhume());
        add(new Feed());
        add(new FeelNoPain());
        add(new FiendFire());
        add(new FireBreathing());
        add(new FlameBarrier());
        add(new Flex());
        add(new GhostlyArmor());
        add(new Havoc());
        add(new Headbutt());
        add(new HeavyBlade());
        add(new Hemokinesis());
        add(new Immolate());
        add(new Impervious());
        add(new InfernalBlade());
        add(new Inflame());
        add(new Intimidate());
        add(new IronWave());
        add(new Juggernaut());
        add(new LimitBreak());
        add(new Metallicize());
        add(new Offering());
        add(new PerfectedStrike());
        add(new PommelStrike());
        add(new PowerThrough());
        add(new Pummel());
        add(new Rage());
        add(new Rampage());
        add(new Reaper());
        add(new RecklessCharge());
        add(new Rupture());
        add(new SearingBlow());
        add(new SecondWind());
        add(new SeeingRed());
        add(new Sentinel());
        add(new SeverSoul());
        add(new Shockwave());
        add(new ShrugItOff());
        add(new SpotWeakness());
        add(new Strike_Red());
        add(new SwordBoomerang());
        add(new ThunderClap());
        add(new TrueGrit());
        add(new TwinStrike());
        add(new Uppercut());
        add(new Warcry());
        add(new Whirlwind());
        add(new WildStrike());
    }

    private static void addGreenCards() {
        add(new Accuracy());
        add(new Acrobatics());
        add(new Adrenaline());
        add(new AfterImage());
        add(new Alchemize());
        add(new AllOutAttack());
        add(new AThousandCuts());
        add(new Backflip());
        add(new Backstab());
        add(new Bane());
        add(new BladeDance());
        add(new Blur());
        add(new BouncingFlask());
        add(new BulletTime());
        add(new Burst());
        add(new CalculatedGamble());
        add(new Caltrops());
        add(new Catalyst());
        add(new Choke());
        add(new CloakAndDagger());
        add(new Concentrate());
        add(new CorpseExplosion());
        add(new CripplingPoison());
        add(new DaggerSpray());
        add(new DaggerThrow());
        add(new Dash());
        add(new DeadlyPoison());
        add(new Defend_Green());
        add(new Deflect());
        add(new DieDieDie());
        add(new Distraction());
        add(new DodgeAndRoll());
        add(new Doppelganger());
        add(new EndlessAgony());
        add(new Envenom());
        add(new EscapePlan());
        add(new Eviscerate());
        add(new Expertise());
        add(new Finisher());
        add(new Flechettes());
        add(new FlyingKnee());
        add(new Footwork());
        add(new GlassKnife());
        add(new GrandFinale());
        add(new HeelHook());
        add(new InfiniteBlades());
        add(new LegSweep());
        add(new Malaise());
        add(new MasterfulStab());
        add(new Neutralize());
        add(new Nightmare());
        add(new NoxiousFumes());
        add(new Outmaneuver());
        add(new PhantasmalKiller());
        add(new PiercingWail());
        add(new PoisonedStab());
        add(new Predator());
        add(new Prepared());
        add(new QuickSlash());
        add(new Reflex());
        add(new RiddleWithHoles());
        add(new Setup());
        add(new Skewer());
        add(new Slice());
        add(new StormOfSteel());
        add(new Strike_Green());
        add(new SuckerPunch());
        add(new Survivor());
        add(new Tactician());
        add(new Terror());
        add(new ToolsOfTheTrade());
        add(new SneakyStrike());
        add(new Unload());
        add(new WellLaidPlans());
        add(new WraithForm());
    }

    private static void addBlueCards() {
        add(new Aggregate());
        add(new AllForOne());
        add(new Amplify());
        add(new AutoShields());
        add(new BallLightning());
        add(new Barrage());
        add(new BeamCell());
        add(new BiasedCognition());
        add(new Blizzard());
        add(new BootSequence());
        add(new Buffer());
        add(new Capacitor());
        add(new Chaos());
        add(new Chill());
        add(new Claw());
        add(new ColdSnap());
        add(new CompileDriver());
        add(new ConserveBattery());
        add(new Consume());
        add(new Coolheaded());
        add(new CoreSurge());
        add(new CreativeAI());
        add(new Darkness());
        add(new Defend_Blue());
        add(new Defragment());
        add(new DoomAndGloom());
        add(new DoubleEnergy());
        add(new Dualcast());
        add(new EchoForm());
        add(new Electrodynamics());
        add(new Fission());
        add(new ForceField());
        add(new FTL());
        add(new Fusion());
        add(new GeneticAlgorithm());
        add(new Glacier());
        add(new GoForTheEyes());
        add(new Heatsinks());
        add(new HelloWorld());
        add(new Hologram());
        add(new Hyperbeam());
        add(new Leap());
        add(new LockOn());
        add(new Loop());
        add(new MachineLearning());
        add(new Melter());
        add(new MeteorStrike());
        add(new MultiCast());
        add(new Overclock());
        add(new Rainbow());
        add(new Reboot());
        add(new Rebound());
        add(new Recursion());
        add(new Recycle());
        add(new ReinforcedBody());
        add(new Reprogram());
        add(new RipAndTear());
        add(new Scrape());
        add(new Seek());
        add(new SelfRepair());
        add(new Skim());
        add(new Stack());
        add(new StaticDischarge());
        add(new SteamBarrier());
        add(new Storm());
        add(new Streamline());
        add(new Strike_Blue());
        add(new Sunder());
        add(new SweepingBeam());
        add(new Tempest());
        add(new ThunderStrike());
        add(new Turbo());
        add(new Equilibrium());
        add(new WhiteNoise());
        add(new Zap());
    }

    private static void addPurpleCards() {
        add(new Alpha());
        add(new BattleHymn());
        add(new Blasphemy());
        add(new BowlingBash());
        add(new Brilliance());
        add(new CarveReality());
        add(new Collect());
        add(new Conclude());
        add(new ConjureBlade());
        add(new Consecrate());
        add(new Crescendo());
        add(new CrushJoints());
        add(new CutThroughFate());
        add(new DeceiveReality());
        add(new Defend_Watcher());
        add(new DeusExMachina());
        add(new DevaForm());
        add(new Devotion());
        add(new EmptyBody());
        add(new EmptyFist());
        add(new EmptyMind());
        add(new Eruption());
        add(new Establishment());
        add(new Evaluate());
        add(new Fasting());
        add(new FearNoEvil());
        add(new FlurryOfBlows());
        add(new FlyingSleeves());
        add(new FollowUp());
        add(new ForeignInfluence());
        add(new Foresight());
        add(new Halt());
        add(new Indignation());
        add(new InnerPeace());
        add(new Judgement());
        add(new JustLucky());
        add(new LessonLearned());
        add(new LikeWater());
        add(new MasterReality());
        add(new Meditate());
        add(new MentalFortress());
        add(new Nirvana());
        add(new Omniscience());
        add(new Perseverance());
        add(new Pray());
        add(new PressurePoints());
        add(new Prostrate());
        add(new Protect());
        add(new Ragnarok());
        add(new ReachHeaven());
        add(new Rushdown());
        add(new Sanctity());
        add(new SandsOfTime());
        add(new SashWhip());
        add(new Scrawl());
        add(new SignatureMove());
        add(new SimmeringFury());
        add(new SpiritShield());
        add(new Strike_Purple());
        add(new Study());
        add(new Swivel());
        add(new TalkToTheHand());
        add(new Tantrum());
        add(new ThirdEye());
        add(new Tranquility());
        add(new Vault());
        add(new Vigilance());
        add(new Wallop());
        add(new WaveOfTheHand());
        add(new Weave());
        add(new WheelKick());
        add(new WindmillStrike());
        add(new Wish());
        add(new Worship());
        add(new WreathOfFlame());
    }

    private static void printMissingPortraitInfo() {
        for (Map.Entry<String, AbstractCard> c : cards.entrySet()) {
            AbstractCard card = c.getValue();
            if (card.jokePortrait == null) {
                System.out.println(card.name + ";" + card.color.name() + ";" + card.type.name());
            }
        }
        for (Map.Entry<String, AbstractCard> c2 : cards.entrySet()) {
            AbstractCard card2 = c2.getValue();
            if (ImageMaster.loadImage("images/1024PortraitsBeta/" + card2.assetUrl + ".png") == null) {
                System.out.println("[INFO] " + card2.name + " missing LARGE beta portrait.");
            }
        }
    }

    private static void printBlueCards(AbstractCard.CardColor color) {
        for (Map.Entry<String, AbstractCard> c : cards.entrySet()) {
            if (c.getValue().color == color) {
                AbstractCard card = c.getValue();
                System.out.println(card.originalName + "; " + card.type.toString() + "; " + card.rarity.toString() + "; " + card.cost + "; " + card.rawDescription);
            }
        }
    }

    private static void addColorlessCards() {
        add(new Apotheosis());
        add(new BandageUp());
        add(new Blind());
        add(new Chrysalis());
        add(new DarkShackles());
        add(new DeepBreath());
        add(new Discovery());
        add(new DramaticEntrance());
        add(new Enlightenment());
        add(new Finesse());
        add(new FlashOfSteel());
        add(new Forethought());
        add(new GoodInstincts());
        add(new HandOfGreed());
        add(new Impatience());
        add(new JackOfAllTrades());
        add(new Madness());
        add(new Magnetism());
        add(new MasterOfStrategy());
        add(new Mayhem());
        add(new Metamorphosis());
        add(new MindBlast());
        add(new Panacea());
        add(new Panache());
        add(new PanicButton());
        add(new Purity());
        add(new SadisticNature());
        add(new SecretTechnique());
        add(new SecretWeapon());
        add(new SwiftStrike());
        add(new TheBomb());
        add(new ThinkingAhead());
        add(new Transmutation());
        add(new Trip());
        add(new Violence());
        add(new Burn());
        add(new Dazed());
        add(new Slimed());
        add(new VoidCard());
        add(new Wound());
        add(new Apparition());
        add(new Beta());
        add(new Bite());
        add(new JAX());
        add(new Insight());
        add(new Miracle());
        add(new Omega());
        add(new RitualDagger());
        add(new Safety());
        add(new Shiv());
        add(new Smite());
        add(new ThroughViolence());
        add(new BecomeAlmighty());
        add(new FameAndFortune());
        add(new LiveForever());
        add(new Expunger());
    }

    private static void addCurseCards() {
        add(new AscendersBane());
        add(new CurseOfTheBell());
        add(new Clumsy());
        add(new Decay());
        add(new Doubt());
        add(new Injury());
        add(new Necronomicurse());
        add(new Normality());
        add(new Pain());
        add(new Parasite());
        add(new Pride());
        add(new Regret());
        add(new Shame());
        add(new Writhe());
    }

    private static void removeNonFinalizedCards() {
        ArrayList<String> toRemove = new ArrayList<>();
        for (Map.Entry<String, AbstractCard> c : cards.entrySet()) {
            if (c.getValue().assetUrl == null) {
                toRemove.add(c.getKey());
            }
        }
        Iterator<String> it = toRemove.iterator();
        while (it.hasNext()) {
            String s = it.next();
            logger.info("Removing Card " + s + " for trailer build.");
            cards.remove(s);
        }
        toRemove.clear();
        for (Map.Entry<String, AbstractCard> c2 : curses.entrySet()) {
            if (c2.getValue().assetUrl == null) {
                toRemove.add(c2.getKey());
            }
        }
        Iterator<String> it2 = toRemove.iterator();
        while (it2.hasNext()) {
            String s2 = it2.next();
            logger.info("Removing Curse " + s2 + " for trailer build.");
            curses.remove(s2);
        }
    }

    public static void unlockAndSeeAllCards() {
        Iterator<String> it = UnlockTracker.lockedCards.iterator();
        while (it.hasNext()) {
            String s = it.next();
            UnlockTracker.hardUnlockOverride(s);
        }
        for (Map.Entry<String, AbstractCard> c : cards.entrySet()) {
            if (c.getValue().rarity != AbstractCard.CardRarity.BASIC && !UnlockTracker.isCardSeen(c.getKey())) {
                UnlockTracker.markCardAsSeen(c.getKey());
            }
        }
        for (Map.Entry<String, AbstractCard> c2 : curses.entrySet()) {
            if (!UnlockTracker.isCardSeen(c2.getKey())) {
                UnlockTracker.markCardAsSeen(c2.getKey());
            }
        }
    }

    public static void add(AbstractCard card) {
        switch (card.color) {
            case RED:
                redCards++;
                if (UnlockTracker.isCardSeen(card.cardID)) {
                    seenRedCards++;
                    break;
                }
                break;
            case GREEN:
                greenCards++;
                if (UnlockTracker.isCardSeen(card.cardID)) {
                    seenGreenCards++;
                    break;
                }
                break;
            case PURPLE:
                purpleCards++;
                if (UnlockTracker.isCardSeen(card.cardID)) {
                    seenPurpleCards++;
                    break;
                }
                break;
            case BLUE:
                blueCards++;
                if (UnlockTracker.isCardSeen(card.cardID)) {
                    seenBlueCards++;
                    break;
                }
                break;
            case COLORLESS:
                colorlessCards++;
                if (UnlockTracker.isCardSeen(card.cardID)) {
                    seenColorlessCards++;
                    break;
                }
                break;
            case CURSE:
                curseCards++;
                if (UnlockTracker.isCardSeen(card.cardID)) {
                    seenCurseCards++;
                }
                curses.put(card.cardID, card);
                break;
        }
        if (!UnlockTracker.isCardSeen(card.cardID)) {
            card.isSeen = false;
        }
        cards.put(card.cardID, card);
        totalCardCount++;
    }

    public static AbstractCard getCopy(String key, int upgradeTime, int misc) {
        AbstractCard retVal;
        AbstractCard source = getCard(key);
        if (source == null) {
            retVal = getCard(Madness.ID).makeCopy();
        } else {
            retVal = getCard(key).makeCopy();
        }
        for (int i = 0; i < upgradeTime; i++) {
            retVal.upgrade();
        }
        retVal.misc = misc;
        if (misc != 0) {
            if (retVal.cardID.equals(GeneticAlgorithm.ID)) {
                retVal.block = misc;
                retVal.baseBlock = misc;
                retVal.initializeDescription();
            }
            if (retVal.cardID.equals(RitualDagger.ID)) {
                retVal.damage = misc;
                retVal.baseDamage = misc;
                retVal.initializeDescription();
            }
        }
        return retVal;
    }

    public static AbstractCard getCopy(String key) {
        return getCard(key).makeCopy();
    }

    public static AbstractCard getCard(AbstractPlayer.PlayerClass plyrClass, String key) {
        return cards.get(key);
    }

    public static AbstractCard getCard(String key) {
        return cards.get(key);
    }

    public static String getCardNameFromMetricID(String metricID) throws NumberFormatException {
        String[] components = metricID.split("\\+");
        String baseId = components[0];
        AbstractCard card = cards.getOrDefault(baseId, null);
        if (card == null) {
            return metricID;
        }
        try {
            if (components.length > 1) {
                card = card.makeCopy();
                int upgrades = Integer.parseInt(components[1]);
                for (int i = 0; i < upgrades; i++) {
                    card.upgrade();
                }
            }
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
        }
        return card.name;
    }

    public static boolean isACard(String metricID) {
        String[] components = metricID.split("\\+");
        String baseId = components[0];
        AbstractCard card = cards.getOrDefault(baseId, null);
        return card != null;
    }

    public static AbstractCard getCurse() {
        ArrayList<String> tmp = new ArrayList<>();
        for (Map.Entry<String, AbstractCard> c : curses.entrySet()) {
            if (!c.getValue().cardID.equals(AscendersBane.ID) && !c.getValue().cardID.equals(Necronomicurse.ID) && !c.getValue().cardID.equals(CurseOfTheBell.ID) && !c.getValue().cardID.equals(Pride.ID)) {
                tmp.add(c.getKey());
            }
        }
        return cards.get(tmp.get(AbstractDungeon.cardRng.random(0, tmp.size() - 1)));
    }

    public static AbstractCard getCurse(AbstractCard prohibitedCard, Random rng) {
        ArrayList<String> tmp = new ArrayList<>();
        for (Map.Entry<String, AbstractCard> c : curses.entrySet()) {
            if (!Objects.equals(c.getValue().cardID, prohibitedCard.cardID) && !Objects.equals(c.getValue().cardID, Necronomicurse.ID) && !Objects.equals(c.getValue().cardID, AscendersBane.ID) && !Objects.equals(c.getValue().cardID, CurseOfTheBell.ID) && !Objects.equals(c.getValue().cardID, Pride.ID)) {
                tmp.add(c.getKey());
            }
        }
        return cards.get(tmp.get(rng.random(0, tmp.size() - 1)));
    }

    public static AbstractCard getCurse(AbstractCard prohibitedCard) {
        return getCurse(prohibitedCard, new Random());
    }

    public static void uploadCardData() {
        ArrayList<String> data = new ArrayList<>();
        for (Map.Entry<String, AbstractCard> c : cards.entrySet()) {
            data.add(c.getValue().gameDataUploadData());
            AbstractCard c2 = c.getValue().makeCopy();
            if (c2.canUpgrade()) {
                c2.upgrade();
                data.add(c2.gameDataUploadData());
            }
        }
        BotDataUploader.uploadDataAsync(BotDataUploader.GameDataType.CARD_DATA, AbstractCard.gameDataUploadHeader(), data);
    }

    public static ArrayList<AbstractCard> getAllCards() {
        ArrayList<AbstractCard> retVal = new ArrayList<>();
        for (Map.Entry<String, AbstractCard> c : cards.entrySet()) {
            retVal.add(c.getValue());
        }
        return retVal;
    }

    public static AbstractCard getAnyColorCard(AbstractCard.CardType type, AbstractCard.CardRarity rarity) {
        CardGroup anyCard = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (Map.Entry<String, AbstractCard> c : cards.entrySet()) {
            if (c.getValue().rarity == rarity && !c.getValue().hasTag(AbstractCard.CardTags.HEALING) && c.getValue().type != AbstractCard.CardType.CURSE && c.getValue().type != AbstractCard.CardType.STATUS && c.getValue().type == type && (!UnlockTracker.isCardLocked(c.getKey()) || Settings.treatEverythingAsUnlocked())) {
                anyCard.addToBottom(c.getValue());
            }
        }
        anyCard.shuffle(AbstractDungeon.cardRandomRng);
        return anyCard.getRandomCard(true, rarity);
    }

    public static AbstractCard getAnyColorCard(AbstractCard.CardRarity rarity) {
        CardGroup anyCard = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (Map.Entry<String, AbstractCard> c : cards.entrySet()) {
            if (c.getValue().rarity == rarity && c.getValue().type != AbstractCard.CardType.CURSE && c.getValue().type != AbstractCard.CardType.STATUS && (!UnlockTracker.isCardLocked(c.getKey()) || Settings.treatEverythingAsUnlocked())) {
                anyCard.addToBottom(c.getValue());
            }
        }
        anyCard.shuffle(AbstractDungeon.cardRng);
        return anyCard.getRandomCard(true, rarity).makeCopy();
    }

    public static CardGroup getEachRare(AbstractPlayer p) {
        CardGroup everyRareCard = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (Map.Entry<String, AbstractCard> c : cards.entrySet()) {
            if (c.getValue().color == p.getCardColor() && c.getValue().rarity == AbstractCard.CardRarity.RARE) {
                everyRareCard.addToBottom(c.getValue().makeCopy());
            }
        }
        return everyRareCard;
    }

    public static ArrayList<AbstractCard> getCardList(LibraryType type) {
        ArrayList<AbstractCard> retVal = new ArrayList<>();
        switch (type) {
            case COLORLESS:
                for (Map.Entry<String, AbstractCard> c : cards.entrySet()) {
                    if (c.getValue().color == AbstractCard.CardColor.COLORLESS) {
                        retVal.add(c.getValue());
                    }
                }
                break;
            case CURSE:
                for (Map.Entry<String, AbstractCard> c2 : cards.entrySet()) {
                    if (c2.getValue().color == AbstractCard.CardColor.CURSE) {
                        retVal.add(c2.getValue());
                    }
                }
                break;
            case RED:
                for (Map.Entry<String, AbstractCard> c3 : cards.entrySet()) {
                    if (c3.getValue().color == AbstractCard.CardColor.RED) {
                        retVal.add(c3.getValue());
                    }
                }
                break;
            case GREEN:
                for (Map.Entry<String, AbstractCard> c4 : cards.entrySet()) {
                    if (c4.getValue().color == AbstractCard.CardColor.GREEN) {
                        retVal.add(c4.getValue());
                    }
                }
                break;
            case BLUE:
                for (Map.Entry<String, AbstractCard> c5 : cards.entrySet()) {
                    if (c5.getValue().color == AbstractCard.CardColor.BLUE) {
                        retVal.add(c5.getValue());
                    }
                }
                break;
            case PURPLE:
                for (Map.Entry<String, AbstractCard> c6 : cards.entrySet()) {
                    if (c6.getValue().color == AbstractCard.CardColor.PURPLE) {
                        retVal.add(c6.getValue());
                    }
                }
                break;
        }
        return retVal;
    }

    public static void addCardsIntoPool(ArrayList<AbstractCard> tmpPool, AbstractCard.CardColor color) {
        logger.info("[INFO] Adding " + color + " cards into card pool.");
        for (Map.Entry<String, AbstractCard> c : cards.entrySet()) {
            AbstractCard card = c.getValue();
            if (card.color == color && card.rarity != AbstractCard.CardRarity.BASIC && card.type != AbstractCard.CardType.STATUS && (!UnlockTracker.isCardLocked(c.getKey()) || Settings.treatEverythingAsUnlocked())) {
                tmpPool.add(card);
            }
        }
    }

    public static void addRedCards(ArrayList<AbstractCard> tmpPool) {
        logger.info("[INFO] Adding red cards into card pool.");
        for (Map.Entry<String, AbstractCard> c : cards.entrySet()) {
            AbstractCard card = c.getValue();
            if (card.color == AbstractCard.CardColor.RED && card.rarity != AbstractCard.CardRarity.BASIC && (!UnlockTracker.isCardLocked(c.getKey()) || Settings.treatEverythingAsUnlocked())) {
                tmpPool.add(card);
            }
        }
    }

    public static void addGreenCards(ArrayList<AbstractCard> tmpPool) {
        logger.info("[INFO] Adding green cards into card pool.");
        for (Map.Entry<String, AbstractCard> c : cards.entrySet()) {
            AbstractCard card = c.getValue();
            if (card.color == AbstractCard.CardColor.GREEN && card.rarity != AbstractCard.CardRarity.BASIC && (!UnlockTracker.isCardLocked(c.getKey()) || Settings.treatEverythingAsUnlocked())) {
                tmpPool.add(card);
            }
        }
    }

    public static void addBlueCards(ArrayList<AbstractCard> tmpPool) {
        logger.info("[INFO] Adding blue cards into card pool.");
        for (Map.Entry<String, AbstractCard> c : cards.entrySet()) {
            AbstractCard card = c.getValue();
            if (card.color == AbstractCard.CardColor.BLUE && card.rarity != AbstractCard.CardRarity.BASIC && (!UnlockTracker.isCardLocked(c.getKey()) || Settings.treatEverythingAsUnlocked())) {
                tmpPool.add(card);
            }
        }
    }

    public static void addPurpleCards(ArrayList<AbstractCard> tmpPool) {
        logger.info("[INFO] Adding purple cards into card pool.");
        for (Map.Entry<String, AbstractCard> c : cards.entrySet()) {
            AbstractCard card = c.getValue();
            if (card.color == AbstractCard.CardColor.PURPLE && card.rarity != AbstractCard.CardRarity.BASIC && (!UnlockTracker.isCardLocked(c.getKey()) || Settings.treatEverythingAsUnlocked())) {
                tmpPool.add(card);
            }
        }
    }

    public static void addColorlessCards(ArrayList<AbstractCard> tmpPool) {
        logger.info("[INFO] Adding colorless cards into card pool.");
        for (Map.Entry<String, AbstractCard> c : cards.entrySet()) {
            AbstractCard card = c.getValue();
            if (card.color == AbstractCard.CardColor.COLORLESS && card.type != AbstractCard.CardType.STATUS && (!UnlockTracker.isCardLocked(c.getKey()) || Settings.treatEverythingAsUnlocked())) {
                tmpPool.add(card);
            }
        }
    }
}
