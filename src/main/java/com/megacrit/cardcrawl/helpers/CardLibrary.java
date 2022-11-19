/*      */ package com.megacrit.cardcrawl.helpers;
/*      */ 
/*      */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*      */ import com.megacrit.cardcrawl.cards.CardGroup;
/*      */ import com.megacrit.cardcrawl.cards.blue.Aggregate;
/*      */ import com.megacrit.cardcrawl.cards.blue.AllForOne;
/*      */ import com.megacrit.cardcrawl.cards.blue.Amplify;
/*      */ import com.megacrit.cardcrawl.cards.blue.AutoShields;
/*      */ import com.megacrit.cardcrawl.cards.blue.BallLightning;
/*      */ import com.megacrit.cardcrawl.cards.blue.Barrage;
/*      */ import com.megacrit.cardcrawl.cards.blue.BeamCell;
/*      */ import com.megacrit.cardcrawl.cards.blue.BiasedCognition;
/*      */ import com.megacrit.cardcrawl.cards.blue.Blizzard;
/*      */ import com.megacrit.cardcrawl.cards.blue.BootSequence;
/*      */ import com.megacrit.cardcrawl.cards.blue.Buffer;
/*      */ import com.megacrit.cardcrawl.cards.blue.Capacitor;
/*      */ import com.megacrit.cardcrawl.cards.blue.Chaos;
/*      */ import com.megacrit.cardcrawl.cards.blue.Chill;
/*      */ import com.megacrit.cardcrawl.cards.blue.Claw;
/*      */ import com.megacrit.cardcrawl.cards.blue.ColdSnap;
/*      */ import com.megacrit.cardcrawl.cards.blue.CompileDriver;
/*      */ import com.megacrit.cardcrawl.cards.blue.ConserveBattery;
/*      */ import com.megacrit.cardcrawl.cards.blue.Consume;
/*      */ import com.megacrit.cardcrawl.cards.blue.Coolheaded;
/*      */ import com.megacrit.cardcrawl.cards.blue.CoreSurge;
/*      */ import com.megacrit.cardcrawl.cards.blue.CreativeAI;
/*      */ import com.megacrit.cardcrawl.cards.blue.Darkness;
/*      */ import com.megacrit.cardcrawl.cards.blue.Defend_Blue;
/*      */ import com.megacrit.cardcrawl.cards.blue.Defragment;
/*      */ import com.megacrit.cardcrawl.cards.blue.DoomAndGloom;
/*      */ import com.megacrit.cardcrawl.cards.blue.DoubleEnergy;
/*      */ import com.megacrit.cardcrawl.cards.blue.Dualcast;
/*      */ import com.megacrit.cardcrawl.cards.blue.EchoForm;
/*      */ import com.megacrit.cardcrawl.cards.blue.Electrodynamics;
/*      */ import com.megacrit.cardcrawl.cards.blue.Equilibrium;
/*      */ import com.megacrit.cardcrawl.cards.blue.FTL;
/*      */ import com.megacrit.cardcrawl.cards.blue.Fission;
/*      */ import com.megacrit.cardcrawl.cards.blue.ForceField;
/*      */ import com.megacrit.cardcrawl.cards.blue.Fusion;
/*      */ import com.megacrit.cardcrawl.cards.blue.GeneticAlgorithm;
/*      */ import com.megacrit.cardcrawl.cards.blue.Glacier;
/*      */ import com.megacrit.cardcrawl.cards.blue.GoForTheEyes;
/*      */ import com.megacrit.cardcrawl.cards.blue.Heatsinks;
/*      */ import com.megacrit.cardcrawl.cards.blue.HelloWorld;
/*      */ import com.megacrit.cardcrawl.cards.blue.Hologram;
/*      */ import com.megacrit.cardcrawl.cards.blue.Hyperbeam;
/*      */ import com.megacrit.cardcrawl.cards.blue.Leap;
/*      */ import com.megacrit.cardcrawl.cards.blue.LockOn;
/*      */ import com.megacrit.cardcrawl.cards.blue.Loop;
/*      */ import com.megacrit.cardcrawl.cards.blue.MachineLearning;
/*      */ import com.megacrit.cardcrawl.cards.blue.Melter;
/*      */ import com.megacrit.cardcrawl.cards.blue.MeteorStrike;
/*      */ import com.megacrit.cardcrawl.cards.blue.MultiCast;
/*      */ import com.megacrit.cardcrawl.cards.blue.Overclock;
/*      */ import com.megacrit.cardcrawl.cards.blue.Rainbow;
/*      */ import com.megacrit.cardcrawl.cards.blue.Reboot;
/*      */ import com.megacrit.cardcrawl.cards.blue.Rebound;
/*      */ import com.megacrit.cardcrawl.cards.blue.Recursion;
/*      */ import com.megacrit.cardcrawl.cards.blue.Recycle;
/*      */ import com.megacrit.cardcrawl.cards.blue.ReinforcedBody;
/*      */ import com.megacrit.cardcrawl.cards.blue.Reprogram;
/*      */ import com.megacrit.cardcrawl.cards.blue.RipAndTear;
/*      */ import com.megacrit.cardcrawl.cards.blue.Scrape;
/*      */ import com.megacrit.cardcrawl.cards.blue.Seek;
/*      */ import com.megacrit.cardcrawl.cards.blue.SelfRepair;
/*      */ import com.megacrit.cardcrawl.cards.blue.Skim;
/*      */ import com.megacrit.cardcrawl.cards.blue.Stack;
/*      */ import com.megacrit.cardcrawl.cards.blue.StaticDischarge;
/*      */ import com.megacrit.cardcrawl.cards.blue.SteamBarrier;
/*      */ import com.megacrit.cardcrawl.cards.blue.Storm;
/*      */ import com.megacrit.cardcrawl.cards.blue.Streamline;
/*      */ import com.megacrit.cardcrawl.cards.blue.Strike_Blue;
/*      */ import com.megacrit.cardcrawl.cards.blue.Sunder;
/*      */ import com.megacrit.cardcrawl.cards.blue.SweepingBeam;
/*      */ import com.megacrit.cardcrawl.cards.blue.Tempest;
/*      */ import com.megacrit.cardcrawl.cards.blue.ThunderStrike;
/*      */ import com.megacrit.cardcrawl.cards.blue.Turbo;
/*      */ import com.megacrit.cardcrawl.cards.blue.WhiteNoise;
/*      */ import com.megacrit.cardcrawl.cards.blue.Zap;
/*      */ import com.megacrit.cardcrawl.cards.colorless.Apotheosis;
/*      */ import com.megacrit.cardcrawl.cards.colorless.Apparition;
/*      */ import com.megacrit.cardcrawl.cards.colorless.BandageUp;
/*      */ import com.megacrit.cardcrawl.cards.colorless.Bite;
/*      */ import com.megacrit.cardcrawl.cards.colorless.Blind;
/*      */ import com.megacrit.cardcrawl.cards.colorless.Chrysalis;
/*      */ import com.megacrit.cardcrawl.cards.colorless.DarkShackles;
/*      */ import com.megacrit.cardcrawl.cards.colorless.DeepBreath;
/*      */ import com.megacrit.cardcrawl.cards.colorless.Discovery;
/*      */ import com.megacrit.cardcrawl.cards.colorless.DramaticEntrance;
/*      */ import com.megacrit.cardcrawl.cards.colorless.Enlightenment;
/*      */ import com.megacrit.cardcrawl.cards.colorless.Finesse;
/*      */ import com.megacrit.cardcrawl.cards.colorless.FlashOfSteel;
/*      */ import com.megacrit.cardcrawl.cards.colorless.Forethought;
/*      */ import com.megacrit.cardcrawl.cards.colorless.GoodInstincts;
/*      */ import com.megacrit.cardcrawl.cards.colorless.HandOfGreed;
/*      */ import com.megacrit.cardcrawl.cards.colorless.Impatience;
/*      */ import com.megacrit.cardcrawl.cards.colorless.JAX;
/*      */ import com.megacrit.cardcrawl.cards.colorless.JackOfAllTrades;
/*      */ import com.megacrit.cardcrawl.cards.colorless.Madness;
/*      */ import com.megacrit.cardcrawl.cards.colorless.Magnetism;
/*      */ import com.megacrit.cardcrawl.cards.colorless.MasterOfStrategy;
/*      */ import com.megacrit.cardcrawl.cards.colorless.Mayhem;
/*      */ import com.megacrit.cardcrawl.cards.colorless.Metamorphosis;
/*      */ import com.megacrit.cardcrawl.cards.colorless.MindBlast;
/*      */ import com.megacrit.cardcrawl.cards.colorless.Panacea;
/*      */ import com.megacrit.cardcrawl.cards.colorless.Panache;
/*      */ import com.megacrit.cardcrawl.cards.colorless.PanicButton;
/*      */ import com.megacrit.cardcrawl.cards.colorless.Purity;
/*      */ import com.megacrit.cardcrawl.cards.colorless.RitualDagger;
/*      */ import com.megacrit.cardcrawl.cards.colorless.SadisticNature;
/*      */ import com.megacrit.cardcrawl.cards.colorless.SecretTechnique;
/*      */ import com.megacrit.cardcrawl.cards.colorless.SecretWeapon;
/*      */ import com.megacrit.cardcrawl.cards.colorless.SwiftStrike;
/*      */ import com.megacrit.cardcrawl.cards.colorless.TheBomb;
/*      */ import com.megacrit.cardcrawl.cards.colorless.ThinkingAhead;
/*      */ import com.megacrit.cardcrawl.cards.colorless.Transmutation;
/*      */ import com.megacrit.cardcrawl.cards.colorless.Trip;
/*      */ import com.megacrit.cardcrawl.cards.colorless.Violence;
/*      */ import com.megacrit.cardcrawl.cards.curses.AscendersBane;
/*      */ import com.megacrit.cardcrawl.cards.curses.Clumsy;
/*      */ import com.megacrit.cardcrawl.cards.curses.CurseOfTheBell;
/*      */ import com.megacrit.cardcrawl.cards.curses.Decay;
/*      */ import com.megacrit.cardcrawl.cards.curses.Doubt;
/*      */ import com.megacrit.cardcrawl.cards.curses.Injury;
/*      */ import com.megacrit.cardcrawl.cards.curses.Necronomicurse;
/*      */ import com.megacrit.cardcrawl.cards.curses.Normality;
/*      */ import com.megacrit.cardcrawl.cards.curses.Pain;
/*      */ import com.megacrit.cardcrawl.cards.curses.Parasite;
/*      */ import com.megacrit.cardcrawl.cards.curses.Pride;
/*      */ import com.megacrit.cardcrawl.cards.curses.Regret;
/*      */ import com.megacrit.cardcrawl.cards.curses.Shame;
/*      */ import com.megacrit.cardcrawl.cards.curses.Writhe;
/*      */ import com.megacrit.cardcrawl.cards.green.AThousandCuts;
/*      */ import com.megacrit.cardcrawl.cards.green.Accuracy;
/*      */ import com.megacrit.cardcrawl.cards.green.Acrobatics;
/*      */ import com.megacrit.cardcrawl.cards.green.Adrenaline;
/*      */ import com.megacrit.cardcrawl.cards.green.AfterImage;
/*      */ import com.megacrit.cardcrawl.cards.green.Alchemize;
/*      */ import com.megacrit.cardcrawl.cards.green.AllOutAttack;
/*      */ import com.megacrit.cardcrawl.cards.green.Backflip;
/*      */ import com.megacrit.cardcrawl.cards.green.Backstab;
/*      */ import com.megacrit.cardcrawl.cards.green.Bane;
/*      */ import com.megacrit.cardcrawl.cards.green.BladeDance;
/*      */ import com.megacrit.cardcrawl.cards.green.Blur;
/*      */ import com.megacrit.cardcrawl.cards.green.BouncingFlask;
/*      */ import com.megacrit.cardcrawl.cards.green.BulletTime;
/*      */ import com.megacrit.cardcrawl.cards.green.Burst;
/*      */ import com.megacrit.cardcrawl.cards.green.CalculatedGamble;
/*      */ import com.megacrit.cardcrawl.cards.green.Caltrops;
/*      */ import com.megacrit.cardcrawl.cards.green.Catalyst;
/*      */ import com.megacrit.cardcrawl.cards.green.Choke;
/*      */ import com.megacrit.cardcrawl.cards.green.CloakAndDagger;
/*      */ import com.megacrit.cardcrawl.cards.green.Concentrate;
/*      */ import com.megacrit.cardcrawl.cards.green.CorpseExplosion;
/*      */ import com.megacrit.cardcrawl.cards.green.CripplingPoison;
/*      */ import com.megacrit.cardcrawl.cards.green.DaggerSpray;
/*      */ import com.megacrit.cardcrawl.cards.green.DaggerThrow;
/*      */ import com.megacrit.cardcrawl.cards.green.Dash;
/*      */ import com.megacrit.cardcrawl.cards.green.DeadlyPoison;
/*      */ import com.megacrit.cardcrawl.cards.green.Defend_Green;
/*      */ import com.megacrit.cardcrawl.cards.green.Deflect;
/*      */ import com.megacrit.cardcrawl.cards.green.DieDieDie;
/*      */ import com.megacrit.cardcrawl.cards.green.Distraction;
/*      */ import com.megacrit.cardcrawl.cards.green.DodgeAndRoll;
/*      */ import com.megacrit.cardcrawl.cards.green.Doppelganger;
/*      */ import com.megacrit.cardcrawl.cards.green.EndlessAgony;
/*      */ import com.megacrit.cardcrawl.cards.green.Envenom;
/*      */ import com.megacrit.cardcrawl.cards.green.EscapePlan;
/*      */ import com.megacrit.cardcrawl.cards.green.Eviscerate;
/*      */ import com.megacrit.cardcrawl.cards.green.Expertise;
/*      */ import com.megacrit.cardcrawl.cards.green.Finisher;
/*      */ import com.megacrit.cardcrawl.cards.green.Flechettes;
/*      */ import com.megacrit.cardcrawl.cards.green.FlyingKnee;
/*      */ import com.megacrit.cardcrawl.cards.green.Footwork;
/*      */ import com.megacrit.cardcrawl.cards.green.GlassKnife;
/*      */ import com.megacrit.cardcrawl.cards.green.GrandFinale;
/*      */ import com.megacrit.cardcrawl.cards.green.HeelHook;
/*      */ import com.megacrit.cardcrawl.cards.green.InfiniteBlades;
/*      */ import com.megacrit.cardcrawl.cards.green.LegSweep;
/*      */ import com.megacrit.cardcrawl.cards.green.Malaise;
/*      */ import com.megacrit.cardcrawl.cards.green.MasterfulStab;
/*      */ import com.megacrit.cardcrawl.cards.green.Neutralize;
/*      */ import com.megacrit.cardcrawl.cards.green.Nightmare;
/*      */ import com.megacrit.cardcrawl.cards.green.NoxiousFumes;
/*      */ import com.megacrit.cardcrawl.cards.green.Outmaneuver;
/*      */ import com.megacrit.cardcrawl.cards.green.PhantasmalKiller;
/*      */ import com.megacrit.cardcrawl.cards.green.PiercingWail;
/*      */ import com.megacrit.cardcrawl.cards.green.PoisonedStab;
/*      */ import com.megacrit.cardcrawl.cards.green.Predator;
/*      */ import com.megacrit.cardcrawl.cards.green.Prepared;
/*      */ import com.megacrit.cardcrawl.cards.green.QuickSlash;
/*      */ import com.megacrit.cardcrawl.cards.green.Reflex;
/*      */ import com.megacrit.cardcrawl.cards.green.RiddleWithHoles;
/*      */ import com.megacrit.cardcrawl.cards.green.Setup;
/*      */ import com.megacrit.cardcrawl.cards.green.Skewer;
/*      */ import com.megacrit.cardcrawl.cards.green.Slice;
/*      */ import com.megacrit.cardcrawl.cards.green.SneakyStrike;
/*      */ import com.megacrit.cardcrawl.cards.green.StormOfSteel;
/*      */ import com.megacrit.cardcrawl.cards.green.Strike_Green;
/*      */ import com.megacrit.cardcrawl.cards.green.SuckerPunch;
/*      */ import com.megacrit.cardcrawl.cards.green.Survivor;
/*      */ import com.megacrit.cardcrawl.cards.green.Tactician;
/*      */ import com.megacrit.cardcrawl.cards.green.Terror;
/*      */ import com.megacrit.cardcrawl.cards.green.ToolsOfTheTrade;
/*      */ import com.megacrit.cardcrawl.cards.green.Unload;
/*      */ import com.megacrit.cardcrawl.cards.green.WellLaidPlans;
/*      */ import com.megacrit.cardcrawl.cards.green.WraithForm;
/*      */ import com.megacrit.cardcrawl.cards.optionCards.BecomeAlmighty;
/*      */ import com.megacrit.cardcrawl.cards.optionCards.FameAndFortune;
/*      */ import com.megacrit.cardcrawl.cards.optionCards.LiveForever;
/*      */ import com.megacrit.cardcrawl.cards.purple.Alpha;
/*      */ import com.megacrit.cardcrawl.cards.purple.BattleHymn;
/*      */ import com.megacrit.cardcrawl.cards.purple.Blasphemy;
/*      */ import com.megacrit.cardcrawl.cards.purple.BowlingBash;
/*      */ import com.megacrit.cardcrawl.cards.purple.Brilliance;
/*      */ import com.megacrit.cardcrawl.cards.purple.CarveReality;
/*      */ import com.megacrit.cardcrawl.cards.purple.Collect;
/*      */ import com.megacrit.cardcrawl.cards.purple.Conclude;
/*      */ import com.megacrit.cardcrawl.cards.purple.ConjureBlade;
/*      */ import com.megacrit.cardcrawl.cards.purple.Consecrate;
/*      */ import com.megacrit.cardcrawl.cards.purple.Crescendo;
/*      */ import com.megacrit.cardcrawl.cards.purple.CrushJoints;
/*      */ import com.megacrit.cardcrawl.cards.purple.CutThroughFate;
/*      */ import com.megacrit.cardcrawl.cards.purple.DeceiveReality;
/*      */ import com.megacrit.cardcrawl.cards.purple.Defend_Watcher;
/*      */ import com.megacrit.cardcrawl.cards.purple.DeusExMachina;
/*      */ import com.megacrit.cardcrawl.cards.purple.DevaForm;
/*      */ import com.megacrit.cardcrawl.cards.purple.Devotion;
/*      */ import com.megacrit.cardcrawl.cards.purple.EmptyBody;
/*      */ import com.megacrit.cardcrawl.cards.purple.EmptyFist;
/*      */ import com.megacrit.cardcrawl.cards.purple.EmptyMind;
/*      */ import com.megacrit.cardcrawl.cards.purple.Eruption;
/*      */ import com.megacrit.cardcrawl.cards.purple.Establishment;
/*      */ import com.megacrit.cardcrawl.cards.purple.Evaluate;
/*      */ import com.megacrit.cardcrawl.cards.purple.Fasting;
/*      */ import com.megacrit.cardcrawl.cards.purple.FearNoEvil;
/*      */ import com.megacrit.cardcrawl.cards.purple.FlurryOfBlows;
/*      */ import com.megacrit.cardcrawl.cards.purple.FlyingSleeves;
/*      */ import com.megacrit.cardcrawl.cards.purple.FollowUp;
/*      */ import com.megacrit.cardcrawl.cards.purple.ForeignInfluence;
/*      */ import com.megacrit.cardcrawl.cards.purple.Foresight;
/*      */ import com.megacrit.cardcrawl.cards.purple.Halt;
/*      */ import com.megacrit.cardcrawl.cards.purple.Indignation;
/*      */ import com.megacrit.cardcrawl.cards.purple.InnerPeace;
/*      */ import com.megacrit.cardcrawl.cards.purple.Judgement;
/*      */ import com.megacrit.cardcrawl.cards.purple.JustLucky;
/*      */ import com.megacrit.cardcrawl.cards.purple.LessonLearned;
/*      */ import com.megacrit.cardcrawl.cards.purple.LikeWater;
/*      */ import com.megacrit.cardcrawl.cards.purple.MasterReality;
/*      */ import com.megacrit.cardcrawl.cards.purple.Meditate;
/*      */ import com.megacrit.cardcrawl.cards.purple.MentalFortress;
/*      */ import com.megacrit.cardcrawl.cards.purple.Nirvana;
/*      */ import com.megacrit.cardcrawl.cards.purple.Omniscience;
/*      */ import com.megacrit.cardcrawl.cards.purple.Perseverance;
/*      */ import com.megacrit.cardcrawl.cards.purple.Pray;
/*      */ import com.megacrit.cardcrawl.cards.purple.PressurePoints;
/*      */ import com.megacrit.cardcrawl.cards.purple.Prostrate;
/*      */ import com.megacrit.cardcrawl.cards.purple.Protect;
/*      */ import com.megacrit.cardcrawl.cards.purple.Ragnarok;
/*      */ import com.megacrit.cardcrawl.cards.purple.ReachHeaven;
/*      */ import com.megacrit.cardcrawl.cards.purple.Rushdown;
/*      */ import com.megacrit.cardcrawl.cards.purple.Sanctity;
/*      */ import com.megacrit.cardcrawl.cards.purple.SandsOfTime;
/*      */ import com.megacrit.cardcrawl.cards.purple.SashWhip;
/*      */ import com.megacrit.cardcrawl.cards.purple.Scrawl;
/*      */ import com.megacrit.cardcrawl.cards.purple.SignatureMove;
/*      */ import com.megacrit.cardcrawl.cards.purple.SimmeringFury;
/*      */ import com.megacrit.cardcrawl.cards.purple.SpiritShield;
/*      */ import com.megacrit.cardcrawl.cards.purple.Strike_Purple;
/*      */ import com.megacrit.cardcrawl.cards.purple.Study;
/*      */ import com.megacrit.cardcrawl.cards.purple.Swivel;
/*      */ import com.megacrit.cardcrawl.cards.purple.TalkToTheHand;
/*      */ import com.megacrit.cardcrawl.cards.purple.Tantrum;
/*      */ import com.megacrit.cardcrawl.cards.purple.ThirdEye;
/*      */ import com.megacrit.cardcrawl.cards.purple.Tranquility;
/*      */ import com.megacrit.cardcrawl.cards.purple.Vault;
/*      */ import com.megacrit.cardcrawl.cards.purple.Vigilance;
/*      */ import com.megacrit.cardcrawl.cards.purple.Wallop;
/*      */ import com.megacrit.cardcrawl.cards.purple.WaveOfTheHand;
/*      */ import com.megacrit.cardcrawl.cards.purple.Weave;
/*      */ import com.megacrit.cardcrawl.cards.purple.WheelKick;
/*      */ import com.megacrit.cardcrawl.cards.purple.WindmillStrike;
/*      */ import com.megacrit.cardcrawl.cards.purple.Wish;
/*      */ import com.megacrit.cardcrawl.cards.purple.Worship;
/*      */ import com.megacrit.cardcrawl.cards.purple.WreathOfFlame;
/*      */ import com.megacrit.cardcrawl.cards.red.Anger;
/*      */ import com.megacrit.cardcrawl.cards.red.Armaments;
/*      */ import com.megacrit.cardcrawl.cards.red.Barricade;
/*      */ import com.megacrit.cardcrawl.cards.red.Bash;
/*      */ import com.megacrit.cardcrawl.cards.red.BattleTrance;
/*      */ import com.megacrit.cardcrawl.cards.red.Berserk;
/*      */ import com.megacrit.cardcrawl.cards.red.BloodForBlood;
/*      */ import com.megacrit.cardcrawl.cards.red.Bloodletting;
/*      */ import com.megacrit.cardcrawl.cards.red.Bludgeon;
/*      */ import com.megacrit.cardcrawl.cards.red.BodySlam;
/*      */ import com.megacrit.cardcrawl.cards.red.Brutality;
/*      */ import com.megacrit.cardcrawl.cards.red.BurningPact;
/*      */ import com.megacrit.cardcrawl.cards.red.Carnage;
/*      */ import com.megacrit.cardcrawl.cards.red.Clash;
/*      */ import com.megacrit.cardcrawl.cards.red.Cleave;
/*      */ import com.megacrit.cardcrawl.cards.red.Clothesline;
/*      */ import com.megacrit.cardcrawl.cards.red.Combust;
/*      */ import com.megacrit.cardcrawl.cards.red.Corruption;
/*      */ import com.megacrit.cardcrawl.cards.red.DarkEmbrace;
/*      */ import com.megacrit.cardcrawl.cards.red.Defend_Red;
/*      */ import com.megacrit.cardcrawl.cards.red.DemonForm;
/*      */ import com.megacrit.cardcrawl.cards.red.Disarm;
/*      */ import com.megacrit.cardcrawl.cards.red.DoubleTap;
/*      */ import com.megacrit.cardcrawl.cards.red.Dropkick;
/*      */ import com.megacrit.cardcrawl.cards.red.DualWield;
/*      */ import com.megacrit.cardcrawl.cards.red.Entrench;
/*      */ import com.megacrit.cardcrawl.cards.red.Evolve;
/*      */ import com.megacrit.cardcrawl.cards.red.Exhume;
/*      */ import com.megacrit.cardcrawl.cards.red.Feed;
/*      */ import com.megacrit.cardcrawl.cards.red.FeelNoPain;
/*      */ import com.megacrit.cardcrawl.cards.red.FiendFire;
/*      */ import com.megacrit.cardcrawl.cards.red.FireBreathing;
/*      */ import com.megacrit.cardcrawl.cards.red.FlameBarrier;
/*      */ import com.megacrit.cardcrawl.cards.red.Flex;
/*      */ import com.megacrit.cardcrawl.cards.red.GhostlyArmor;
/*      */ import com.megacrit.cardcrawl.cards.red.Havoc;
/*      */ import com.megacrit.cardcrawl.cards.red.Headbutt;
/*      */ import com.megacrit.cardcrawl.cards.red.HeavyBlade;
/*      */ import com.megacrit.cardcrawl.cards.red.Hemokinesis;
/*      */ import com.megacrit.cardcrawl.cards.red.Immolate;
/*      */ import com.megacrit.cardcrawl.cards.red.Impervious;
/*      */ import com.megacrit.cardcrawl.cards.red.InfernalBlade;
/*      */ import com.megacrit.cardcrawl.cards.red.Inflame;
/*      */ import com.megacrit.cardcrawl.cards.red.Intimidate;
/*      */ import com.megacrit.cardcrawl.cards.red.IronWave;
/*      */ import com.megacrit.cardcrawl.cards.red.Juggernaut;
/*      */ import com.megacrit.cardcrawl.cards.red.LimitBreak;
/*      */ import com.megacrit.cardcrawl.cards.red.Metallicize;
/*      */ import com.megacrit.cardcrawl.cards.red.Offering;
/*      */ import com.megacrit.cardcrawl.cards.red.PerfectedStrike;
/*      */ import com.megacrit.cardcrawl.cards.red.PommelStrike;
/*      */ import com.megacrit.cardcrawl.cards.red.PowerThrough;
/*      */ import com.megacrit.cardcrawl.cards.red.Pummel;
/*      */ import com.megacrit.cardcrawl.cards.red.Rage;
/*      */ import com.megacrit.cardcrawl.cards.red.Rampage;
/*      */ import com.megacrit.cardcrawl.cards.red.Reaper;
/*      */ import com.megacrit.cardcrawl.cards.red.RecklessCharge;
/*      */ import com.megacrit.cardcrawl.cards.red.Rupture;
/*      */ import com.megacrit.cardcrawl.cards.red.SearingBlow;
/*      */ import com.megacrit.cardcrawl.cards.red.SecondWind;
/*      */ import com.megacrit.cardcrawl.cards.red.SeeingRed;
/*      */ import com.megacrit.cardcrawl.cards.red.Sentinel;
/*      */ import com.megacrit.cardcrawl.cards.red.SeverSoul;
/*      */ import com.megacrit.cardcrawl.cards.red.Shockwave;
/*      */ import com.megacrit.cardcrawl.cards.red.ShrugItOff;
/*      */ import com.megacrit.cardcrawl.cards.red.SpotWeakness;
/*      */ import com.megacrit.cardcrawl.cards.red.Strike_Red;
/*      */ import com.megacrit.cardcrawl.cards.red.SwordBoomerang;
/*      */ import com.megacrit.cardcrawl.cards.red.ThunderClap;
/*      */ import com.megacrit.cardcrawl.cards.red.TrueGrit;
/*      */ import com.megacrit.cardcrawl.cards.red.TwinStrike;
/*      */ import com.megacrit.cardcrawl.cards.red.Uppercut;
/*      */ import com.megacrit.cardcrawl.cards.red.Warcry;
/*      */ import com.megacrit.cardcrawl.cards.red.Whirlwind;
/*      */ import com.megacrit.cardcrawl.cards.red.WildStrike;
/*      */ import com.megacrit.cardcrawl.cards.status.Burn;
/*      */ import com.megacrit.cardcrawl.cards.status.Dazed;
/*      */ import com.megacrit.cardcrawl.cards.status.Slimed;
/*      */ import com.megacrit.cardcrawl.cards.status.VoidCard;
/*      */ import com.megacrit.cardcrawl.cards.status.Wound;
/*      */ import com.megacrit.cardcrawl.cards.tempCards.Beta;
/*      */ import com.megacrit.cardcrawl.cards.tempCards.Expunger;
/*      */ import com.megacrit.cardcrawl.cards.tempCards.Insight;
/*      */ import com.megacrit.cardcrawl.cards.tempCards.Miracle;
/*      */ import com.megacrit.cardcrawl.cards.tempCards.Omega;
/*      */ import com.megacrit.cardcrawl.cards.tempCards.Safety;
/*      */ import com.megacrit.cardcrawl.cards.tempCards.Shiv;
/*      */ import com.megacrit.cardcrawl.cards.tempCards.Smite;
/*      */ import com.megacrit.cardcrawl.cards.tempCards.ThroughViolence;
/*      */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*      */ import com.megacrit.cardcrawl.core.Settings;
/*      */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*      */ import com.megacrit.cardcrawl.metrics.BotDataUploader;
/*      */ import com.megacrit.cardcrawl.random.Random;
/*      */ import com.megacrit.cardcrawl.unlock.UnlockTracker;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.Map;
/*      */ import java.util.Objects;
/*      */ import org.apache.logging.log4j.LogManager;
/*      */ import org.apache.logging.log4j.Logger;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class CardLibrary
/*      */ {
/*  397 */   private static final Logger logger = LogManager.getLogger(CardLibrary.class.getName());
/*  398 */   public static int totalCardCount = 0;
/*  399 */   public static HashMap<String, AbstractCard> cards = new HashMap<>();
/*  400 */   private static HashMap<String, AbstractCard> curses = new HashMap<>();
/*  401 */   public static int redCards = 0, greenCards = 0, blueCards = 0, purpleCards = 0, colorlessCards = 0, curseCards = 0;
/*  402 */   public static int seenRedCards = 0, seenGreenCards = 0, seenBlueCards = 0, seenPurpleCards = 0;
/*  403 */   public static int seenColorlessCards = 0, seenCurseCards = 0;
/*      */   
/*      */   public enum LibraryType {
/*  406 */     RED, GREEN, BLUE, PURPLE, CURSE, COLORLESS;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void initialize() {
/*  413 */     long startTime = System.currentTimeMillis();
/*      */     
/*  415 */     addRedCards();
/*  416 */     addGreenCards();
/*  417 */     addBlueCards();
/*  418 */     addPurpleCards();
/*  419 */     addColorlessCards();
/*  420 */     addCurseCards();
/*      */     
/*  422 */     if (Settings.isDev);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  430 */     logger.info("Card load time: " + (
/*  431 */         System.currentTimeMillis() - startTime) + "ms with " + cards.size() + " cards");
/*      */     
/*  433 */     if (Settings.isDev) {
/*  434 */       logger.info("[INFO] Red Cards: \t" + redCards);
/*  435 */       logger.info("[INFO] Green Cards: \t" + greenCards);
/*  436 */       logger.info("[INFO] Blue Cards: \t" + blueCards);
/*  437 */       logger.info("[INFO] Purple Cards: \t" + purpleCards);
/*  438 */       logger.info("[INFO] Colorless Cards: \t" + colorlessCards);
/*  439 */       logger.info("[INFO] Curse Cards: \t" + curseCards);
/*  440 */       logger.info("[INFO] Total Cards: \t" + (redCards + greenCards + blueCards + purpleCards + colorlessCards + curseCards));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static void resetForReload() {
/*  447 */     cards = new HashMap<>();
/*  448 */     curses = new HashMap<>();
/*  449 */     totalCardCount = 0;
/*  450 */     redCards = 0;
/*  451 */     greenCards = 0;
/*  452 */     blueCards = 0;
/*  453 */     purpleCards = 0;
/*  454 */     colorlessCards = 0;
/*  455 */     curseCards = 0;
/*  456 */     seenRedCards = 0;
/*  457 */     seenGreenCards = 0;
/*  458 */     seenBlueCards = 0;
/*  459 */     seenPurpleCards = 0;
/*  460 */     seenColorlessCards = 0;
/*  461 */     seenCurseCards = 0;
/*      */   }
/*      */   
/*      */   private static void addRedCards() {
/*  465 */     add((AbstractCard)new Anger());
/*  466 */     add((AbstractCard)new Armaments());
/*  467 */     add((AbstractCard)new Barricade());
/*  468 */     add((AbstractCard)new Bash());
/*  469 */     add((AbstractCard)new BattleTrance());
/*  470 */     add((AbstractCard)new Berserk());
/*  471 */     add((AbstractCard)new BloodForBlood());
/*  472 */     add((AbstractCard)new Bloodletting());
/*  473 */     add((AbstractCard)new Bludgeon());
/*  474 */     add((AbstractCard)new BodySlam());
/*  475 */     add((AbstractCard)new Brutality());
/*  476 */     add((AbstractCard)new BurningPact());
/*  477 */     add((AbstractCard)new Carnage());
/*  478 */     add((AbstractCard)new Clash());
/*  479 */     add((AbstractCard)new Cleave());
/*  480 */     add((AbstractCard)new Clothesline());
/*  481 */     add((AbstractCard)new Combust());
/*  482 */     add((AbstractCard)new Corruption());
/*  483 */     add((AbstractCard)new DarkEmbrace());
/*  484 */     add((AbstractCard)new Defend_Red());
/*  485 */     add((AbstractCard)new DemonForm());
/*  486 */     add((AbstractCard)new Disarm());
/*  487 */     add((AbstractCard)new DoubleTap());
/*  488 */     add((AbstractCard)new Dropkick());
/*  489 */     add((AbstractCard)new DualWield());
/*  490 */     add((AbstractCard)new Entrench());
/*  491 */     add((AbstractCard)new Evolve());
/*  492 */     add((AbstractCard)new Exhume());
/*  493 */     add((AbstractCard)new Feed());
/*  494 */     add((AbstractCard)new FeelNoPain());
/*  495 */     add((AbstractCard)new FiendFire());
/*  496 */     add((AbstractCard)new FireBreathing());
/*  497 */     add((AbstractCard)new FlameBarrier());
/*  498 */     add((AbstractCard)new Flex());
/*  499 */     add((AbstractCard)new GhostlyArmor());
/*  500 */     add((AbstractCard)new Havoc());
/*  501 */     add((AbstractCard)new Headbutt());
/*  502 */     add((AbstractCard)new HeavyBlade());
/*  503 */     add((AbstractCard)new Hemokinesis());
/*  504 */     add((AbstractCard)new Immolate());
/*  505 */     add((AbstractCard)new Impervious());
/*  506 */     add((AbstractCard)new InfernalBlade());
/*  507 */     add((AbstractCard)new Inflame());
/*  508 */     add((AbstractCard)new Intimidate());
/*  509 */     add((AbstractCard)new IronWave());
/*  510 */     add((AbstractCard)new Juggernaut());
/*  511 */     add((AbstractCard)new LimitBreak());
/*  512 */     add((AbstractCard)new Metallicize());
/*  513 */     add((AbstractCard)new Offering());
/*  514 */     add((AbstractCard)new PerfectedStrike());
/*  515 */     add((AbstractCard)new PommelStrike());
/*  516 */     add((AbstractCard)new PowerThrough());
/*  517 */     add((AbstractCard)new Pummel());
/*  518 */     add((AbstractCard)new Rage());
/*  519 */     add((AbstractCard)new Rampage());
/*  520 */     add((AbstractCard)new Reaper());
/*  521 */     add((AbstractCard)new RecklessCharge());
/*  522 */     add((AbstractCard)new Rupture());
/*  523 */     add((AbstractCard)new SearingBlow());
/*  524 */     add((AbstractCard)new SecondWind());
/*  525 */     add((AbstractCard)new SeeingRed());
/*  526 */     add((AbstractCard)new Sentinel());
/*  527 */     add((AbstractCard)new SeverSoul());
/*  528 */     add((AbstractCard)new Shockwave());
/*  529 */     add((AbstractCard)new ShrugItOff());
/*  530 */     add((AbstractCard)new SpotWeakness());
/*  531 */     add((AbstractCard)new Strike_Red());
/*  532 */     add((AbstractCard)new SwordBoomerang());
/*  533 */     add((AbstractCard)new ThunderClap());
/*  534 */     add((AbstractCard)new TrueGrit());
/*  535 */     add((AbstractCard)new TwinStrike());
/*  536 */     add((AbstractCard)new Uppercut());
/*  537 */     add((AbstractCard)new Warcry());
/*  538 */     add((AbstractCard)new Whirlwind());
/*  539 */     add((AbstractCard)new WildStrike());
/*      */   }
/*      */   
/*      */   private static void addGreenCards() {
/*  543 */     add((AbstractCard)new Accuracy());
/*  544 */     add((AbstractCard)new Acrobatics());
/*  545 */     add((AbstractCard)new Adrenaline());
/*  546 */     add((AbstractCard)new AfterImage());
/*  547 */     add((AbstractCard)new Alchemize());
/*  548 */     add((AbstractCard)new AllOutAttack());
/*  549 */     add((AbstractCard)new AThousandCuts());
/*  550 */     add((AbstractCard)new Backflip());
/*  551 */     add((AbstractCard)new Backstab());
/*  552 */     add((AbstractCard)new Bane());
/*  553 */     add((AbstractCard)new BladeDance());
/*  554 */     add((AbstractCard)new Blur());
/*  555 */     add((AbstractCard)new BouncingFlask());
/*  556 */     add((AbstractCard)new BulletTime());
/*  557 */     add((AbstractCard)new Burst());
/*  558 */     add((AbstractCard)new CalculatedGamble());
/*  559 */     add((AbstractCard)new Caltrops());
/*  560 */     add((AbstractCard)new Catalyst());
/*  561 */     add((AbstractCard)new Choke());
/*  562 */     add((AbstractCard)new CloakAndDagger());
/*  563 */     add((AbstractCard)new Concentrate());
/*  564 */     add((AbstractCard)new CorpseExplosion());
/*  565 */     add((AbstractCard)new CripplingPoison());
/*  566 */     add((AbstractCard)new DaggerSpray());
/*  567 */     add((AbstractCard)new DaggerThrow());
/*  568 */     add((AbstractCard)new Dash());
/*  569 */     add((AbstractCard)new DeadlyPoison());
/*  570 */     add((AbstractCard)new Defend_Green());
/*  571 */     add((AbstractCard)new Deflect());
/*  572 */     add((AbstractCard)new DieDieDie());
/*  573 */     add((AbstractCard)new Distraction());
/*  574 */     add((AbstractCard)new DodgeAndRoll());
/*  575 */     add((AbstractCard)new Doppelganger());
/*  576 */     add((AbstractCard)new EndlessAgony());
/*  577 */     add((AbstractCard)new Envenom());
/*  578 */     add((AbstractCard)new EscapePlan());
/*  579 */     add((AbstractCard)new Eviscerate());
/*  580 */     add((AbstractCard)new Expertise());
/*  581 */     add((AbstractCard)new Finisher());
/*  582 */     add((AbstractCard)new Flechettes());
/*  583 */     add((AbstractCard)new FlyingKnee());
/*  584 */     add((AbstractCard)new Footwork());
/*  585 */     add((AbstractCard)new GlassKnife());
/*  586 */     add((AbstractCard)new GrandFinale());
/*  587 */     add((AbstractCard)new HeelHook());
/*  588 */     add((AbstractCard)new InfiniteBlades());
/*  589 */     add((AbstractCard)new LegSweep());
/*  590 */     add((AbstractCard)new Malaise());
/*  591 */     add((AbstractCard)new MasterfulStab());
/*  592 */     add((AbstractCard)new Neutralize());
/*  593 */     add((AbstractCard)new Nightmare());
/*  594 */     add((AbstractCard)new NoxiousFumes());
/*  595 */     add((AbstractCard)new Outmaneuver());
/*  596 */     add((AbstractCard)new PhantasmalKiller());
/*  597 */     add((AbstractCard)new PiercingWail());
/*  598 */     add((AbstractCard)new PoisonedStab());
/*  599 */     add((AbstractCard)new Predator());
/*  600 */     add((AbstractCard)new Prepared());
/*  601 */     add((AbstractCard)new QuickSlash());
/*  602 */     add((AbstractCard)new Reflex());
/*  603 */     add((AbstractCard)new RiddleWithHoles());
/*  604 */     add((AbstractCard)new Setup());
/*  605 */     add((AbstractCard)new Skewer());
/*  606 */     add((AbstractCard)new Slice());
/*  607 */     add((AbstractCard)new StormOfSteel());
/*  608 */     add((AbstractCard)new Strike_Green());
/*  609 */     add((AbstractCard)new SuckerPunch());
/*  610 */     add((AbstractCard)new Survivor());
/*  611 */     add((AbstractCard)new Tactician());
/*  612 */     add((AbstractCard)new Terror());
/*  613 */     add((AbstractCard)new ToolsOfTheTrade());
/*  614 */     add((AbstractCard)new SneakyStrike());
/*  615 */     add((AbstractCard)new Unload());
/*  616 */     add((AbstractCard)new WellLaidPlans());
/*  617 */     add((AbstractCard)new WraithForm());
/*      */   }
/*      */   
/*      */   private static void addBlueCards() {
/*  621 */     add((AbstractCard)new Aggregate());
/*  622 */     add((AbstractCard)new AllForOne());
/*  623 */     add((AbstractCard)new Amplify());
/*  624 */     add((AbstractCard)new AutoShields());
/*  625 */     add((AbstractCard)new BallLightning());
/*  626 */     add((AbstractCard)new Barrage());
/*  627 */     add((AbstractCard)new BeamCell());
/*  628 */     add((AbstractCard)new BiasedCognition());
/*  629 */     add((AbstractCard)new Blizzard());
/*  630 */     add((AbstractCard)new BootSequence());
/*  631 */     add((AbstractCard)new Buffer());
/*  632 */     add((AbstractCard)new Capacitor());
/*  633 */     add((AbstractCard)new Chaos());
/*  634 */     add((AbstractCard)new Chill());
/*  635 */     add((AbstractCard)new Claw());
/*  636 */     add((AbstractCard)new ColdSnap());
/*  637 */     add((AbstractCard)new CompileDriver());
/*  638 */     add((AbstractCard)new ConserveBattery());
/*  639 */     add((AbstractCard)new Consume());
/*  640 */     add((AbstractCard)new Coolheaded());
/*  641 */     add((AbstractCard)new CoreSurge());
/*  642 */     add((AbstractCard)new CreativeAI());
/*  643 */     add((AbstractCard)new Darkness());
/*  644 */     add((AbstractCard)new Defend_Blue());
/*  645 */     add((AbstractCard)new Defragment());
/*  646 */     add((AbstractCard)new DoomAndGloom());
/*  647 */     add((AbstractCard)new DoubleEnergy());
/*  648 */     add((AbstractCard)new Dualcast());
/*  649 */     add((AbstractCard)new EchoForm());
/*  650 */     add((AbstractCard)new Electrodynamics());
/*  651 */     add((AbstractCard)new Fission());
/*  652 */     add((AbstractCard)new ForceField());
/*  653 */     add((AbstractCard)new FTL());
/*  654 */     add((AbstractCard)new Fusion());
/*  655 */     add((AbstractCard)new GeneticAlgorithm());
/*  656 */     add((AbstractCard)new Glacier());
/*  657 */     add((AbstractCard)new GoForTheEyes());
/*  658 */     add((AbstractCard)new Heatsinks());
/*  659 */     add((AbstractCard)new HelloWorld());
/*  660 */     add((AbstractCard)new Hologram());
/*  661 */     add((AbstractCard)new Hyperbeam());
/*  662 */     add((AbstractCard)new Leap());
/*  663 */     add((AbstractCard)new LockOn());
/*  664 */     add((AbstractCard)new Loop());
/*  665 */     add((AbstractCard)new MachineLearning());
/*  666 */     add((AbstractCard)new Melter());
/*  667 */     add((AbstractCard)new MeteorStrike());
/*  668 */     add((AbstractCard)new MultiCast());
/*  669 */     add((AbstractCard)new Overclock());
/*  670 */     add((AbstractCard)new Rainbow());
/*  671 */     add((AbstractCard)new Reboot());
/*  672 */     add((AbstractCard)new Rebound());
/*  673 */     add((AbstractCard)new Recursion());
/*  674 */     add((AbstractCard)new Recycle());
/*  675 */     add((AbstractCard)new ReinforcedBody());
/*  676 */     add((AbstractCard)new Reprogram());
/*  677 */     add((AbstractCard)new RipAndTear());
/*  678 */     add((AbstractCard)new Scrape());
/*  679 */     add((AbstractCard)new Seek());
/*  680 */     add((AbstractCard)new SelfRepair());
/*  681 */     add((AbstractCard)new Skim());
/*  682 */     add((AbstractCard)new Stack());
/*  683 */     add((AbstractCard)new StaticDischarge());
/*  684 */     add((AbstractCard)new SteamBarrier());
/*  685 */     add((AbstractCard)new Storm());
/*  686 */     add((AbstractCard)new Streamline());
/*  687 */     add((AbstractCard)new Strike_Blue());
/*  688 */     add((AbstractCard)new Sunder());
/*  689 */     add((AbstractCard)new SweepingBeam());
/*  690 */     add((AbstractCard)new Tempest());
/*  691 */     add((AbstractCard)new ThunderStrike());
/*  692 */     add((AbstractCard)new Turbo());
/*  693 */     add((AbstractCard)new Equilibrium());
/*  694 */     add((AbstractCard)new WhiteNoise());
/*  695 */     add((AbstractCard)new Zap());
/*      */   }
/*      */   
/*      */   private static void addPurpleCards() {
/*  699 */     add((AbstractCard)new Alpha());
/*  700 */     add((AbstractCard)new BattleHymn());
/*  701 */     add((AbstractCard)new Blasphemy());
/*  702 */     add((AbstractCard)new BowlingBash());
/*  703 */     add((AbstractCard)new Brilliance());
/*  704 */     add((AbstractCard)new CarveReality());
/*  705 */     add((AbstractCard)new Collect());
/*  706 */     add((AbstractCard)new Conclude());
/*  707 */     add((AbstractCard)new ConjureBlade());
/*  708 */     add((AbstractCard)new Consecrate());
/*  709 */     add((AbstractCard)new Crescendo());
/*  710 */     add((AbstractCard)new CrushJoints());
/*  711 */     add((AbstractCard)new CutThroughFate());
/*  712 */     add((AbstractCard)new DeceiveReality());
/*  713 */     add((AbstractCard)new Defend_Watcher());
/*  714 */     add((AbstractCard)new DeusExMachina());
/*  715 */     add((AbstractCard)new DevaForm());
/*  716 */     add((AbstractCard)new Devotion());
/*  717 */     add((AbstractCard)new EmptyBody());
/*  718 */     add((AbstractCard)new EmptyFist());
/*  719 */     add((AbstractCard)new EmptyMind());
/*  720 */     add((AbstractCard)new Eruption());
/*  721 */     add((AbstractCard)new Establishment());
/*  722 */     add((AbstractCard)new Evaluate());
/*  723 */     add((AbstractCard)new Fasting());
/*  724 */     add((AbstractCard)new FearNoEvil());
/*  725 */     add((AbstractCard)new FlurryOfBlows());
/*  726 */     add((AbstractCard)new FlyingSleeves());
/*  727 */     add((AbstractCard)new FollowUp());
/*  728 */     add((AbstractCard)new ForeignInfluence());
/*  729 */     add((AbstractCard)new Foresight());
/*  730 */     add((AbstractCard)new Halt());
/*  731 */     add((AbstractCard)new Indignation());
/*  732 */     add((AbstractCard)new InnerPeace());
/*  733 */     add((AbstractCard)new Judgement());
/*  734 */     add((AbstractCard)new JustLucky());
/*  735 */     add((AbstractCard)new LessonLearned());
/*  736 */     add((AbstractCard)new LikeWater());
/*  737 */     add((AbstractCard)new MasterReality());
/*  738 */     add((AbstractCard)new Meditate());
/*  739 */     add((AbstractCard)new MentalFortress());
/*  740 */     add((AbstractCard)new Nirvana());
/*  741 */     add((AbstractCard)new Omniscience());
/*  742 */     add((AbstractCard)new Perseverance());
/*  743 */     add((AbstractCard)new Pray());
/*  744 */     add((AbstractCard)new PressurePoints());
/*  745 */     add((AbstractCard)new Prostrate());
/*  746 */     add((AbstractCard)new Protect());
/*  747 */     add((AbstractCard)new Ragnarok());
/*  748 */     add((AbstractCard)new ReachHeaven());
/*  749 */     add((AbstractCard)new Rushdown());
/*  750 */     add((AbstractCard)new Sanctity());
/*  751 */     add((AbstractCard)new SandsOfTime());
/*  752 */     add((AbstractCard)new SashWhip());
/*  753 */     add((AbstractCard)new Scrawl());
/*  754 */     add((AbstractCard)new SignatureMove());
/*  755 */     add((AbstractCard)new SimmeringFury());
/*  756 */     add((AbstractCard)new SpiritShield());
/*  757 */     add((AbstractCard)new Strike_Purple());
/*  758 */     add((AbstractCard)new Study());
/*  759 */     add((AbstractCard)new Swivel());
/*  760 */     add((AbstractCard)new TalkToTheHand());
/*  761 */     add((AbstractCard)new Tantrum());
/*  762 */     add((AbstractCard)new ThirdEye());
/*  763 */     add((AbstractCard)new Tranquility());
/*  764 */     add((AbstractCard)new Vault());
/*  765 */     add((AbstractCard)new Vigilance());
/*  766 */     add((AbstractCard)new Wallop());
/*  767 */     add((AbstractCard)new WaveOfTheHand());
/*  768 */     add((AbstractCard)new Weave());
/*  769 */     add((AbstractCard)new WheelKick());
/*  770 */     add((AbstractCard)new WindmillStrike());
/*  771 */     add((AbstractCard)new Wish());
/*  772 */     add((AbstractCard)new Worship());
/*  773 */     add((AbstractCard)new WreathOfFlame());
/*      */   }
/*      */ 
/*      */   
/*      */   private static void printMissingPortraitInfo() {
/*  778 */     for (Map.Entry<String, AbstractCard> c : cards.entrySet()) {
/*  779 */       AbstractCard card = c.getValue();
/*  780 */       if (card.jokePortrait == null) {
/*  781 */         System.out.println(card.name + ";" + card.color.name() + ";" + card.type.name());
/*      */       }
/*      */     } 
/*      */     
/*  785 */     for (Map.Entry<String, AbstractCard> c : cards.entrySet()) {
/*  786 */       AbstractCard card = c.getValue();
/*  787 */       if (ImageMaster.loadImage("images/1024PortraitsBeta/" + card.assetUrl + ".png") == null) {
/*  788 */         System.out.println("[INFO] " + card.name + " missing LARGE beta portrait.");
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static void printBlueCards(AbstractCard.CardColor color) {
/*  796 */     for (Map.Entry<String, AbstractCard> c : cards.entrySet()) {
/*  797 */       if (((AbstractCard)c.getValue()).color == color) {
/*  798 */         AbstractCard card = c.getValue();
/*  799 */         System.out.println(card.originalName + "; " + card.type
/*  800 */             .toString() + "; " + card.rarity.toString() + "; " + card.cost + "; " + card.rawDescription);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private static void addColorlessCards() {
/*  807 */     add((AbstractCard)new Apotheosis());
/*  808 */     add((AbstractCard)new BandageUp());
/*  809 */     add((AbstractCard)new Blind());
/*  810 */     add((AbstractCard)new Chrysalis());
/*  811 */     add((AbstractCard)new DarkShackles());
/*  812 */     add((AbstractCard)new DeepBreath());
/*  813 */     add((AbstractCard)new Discovery());
/*  814 */     add((AbstractCard)new DramaticEntrance());
/*  815 */     add((AbstractCard)new Enlightenment());
/*  816 */     add((AbstractCard)new Finesse());
/*  817 */     add((AbstractCard)new FlashOfSteel());
/*  818 */     add((AbstractCard)new Forethought());
/*  819 */     add((AbstractCard)new GoodInstincts());
/*  820 */     add((AbstractCard)new HandOfGreed());
/*  821 */     add((AbstractCard)new Impatience());
/*  822 */     add((AbstractCard)new JackOfAllTrades());
/*  823 */     add((AbstractCard)new Madness());
/*  824 */     add((AbstractCard)new Magnetism());
/*  825 */     add((AbstractCard)new MasterOfStrategy());
/*  826 */     add((AbstractCard)new Mayhem());
/*  827 */     add((AbstractCard)new Metamorphosis());
/*  828 */     add((AbstractCard)new MindBlast());
/*  829 */     add((AbstractCard)new Panacea());
/*  830 */     add((AbstractCard)new Panache());
/*  831 */     add((AbstractCard)new PanicButton());
/*  832 */     add((AbstractCard)new Purity());
/*  833 */     add((AbstractCard)new SadisticNature());
/*  834 */     add((AbstractCard)new SecretTechnique());
/*  835 */     add((AbstractCard)new SecretWeapon());
/*  836 */     add((AbstractCard)new SwiftStrike());
/*  837 */     add((AbstractCard)new TheBomb());
/*  838 */     add((AbstractCard)new ThinkingAhead());
/*  839 */     add((AbstractCard)new Transmutation());
/*  840 */     add((AbstractCard)new Trip());
/*  841 */     add((AbstractCard)new Violence());
/*      */ 
/*      */     
/*  844 */     add((AbstractCard)new Burn());
/*  845 */     add((AbstractCard)new Dazed());
/*  846 */     add((AbstractCard)new Slimed());
/*  847 */     add((AbstractCard)new VoidCard());
/*  848 */     add((AbstractCard)new Wound());
/*      */ 
/*      */     
/*  851 */     add((AbstractCard)new Apparition());
/*  852 */     add((AbstractCard)new Beta());
/*  853 */     add((AbstractCard)new Bite());
/*  854 */     add((AbstractCard)new JAX());
/*  855 */     add((AbstractCard)new Insight());
/*  856 */     add((AbstractCard)new Miracle());
/*  857 */     add((AbstractCard)new Omega());
/*  858 */     add((AbstractCard)new RitualDagger());
/*  859 */     add((AbstractCard)new Safety());
/*  860 */     add((AbstractCard)new Shiv());
/*  861 */     add((AbstractCard)new Smite());
/*  862 */     add((AbstractCard)new ThroughViolence());
/*  863 */     add((AbstractCard)new BecomeAlmighty());
/*  864 */     add((AbstractCard)new FameAndFortune());
/*  865 */     add((AbstractCard)new LiveForever());
/*  866 */     add((AbstractCard)new Expunger());
/*      */   }
/*      */   
/*      */   private static void addCurseCards() {
/*  870 */     add((AbstractCard)new AscendersBane());
/*  871 */     add((AbstractCard)new CurseOfTheBell());
/*  872 */     add((AbstractCard)new Clumsy());
/*  873 */     add((AbstractCard)new Decay());
/*  874 */     add((AbstractCard)new Doubt());
/*  875 */     add((AbstractCard)new Injury());
/*  876 */     add((AbstractCard)new Necronomicurse());
/*  877 */     add((AbstractCard)new Normality());
/*  878 */     add((AbstractCard)new Pain());
/*  879 */     add((AbstractCard)new Parasite());
/*  880 */     add((AbstractCard)new Pride());
/*  881 */     add((AbstractCard)new Regret());
/*  882 */     add((AbstractCard)new Shame());
/*  883 */     add((AbstractCard)new Writhe());
/*      */   }
/*      */ 
/*      */   
/*      */   private static void removeNonFinalizedCards() {
/*  888 */     ArrayList<String> toRemove = new ArrayList<>();
/*  889 */     for (Map.Entry<String, AbstractCard> c : cards.entrySet()) {
/*  890 */       if (((AbstractCard)c.getValue()).assetUrl == null) {
/*  891 */         toRemove.add(c.getKey());
/*      */       }
/*      */     } 
/*      */     
/*  895 */     for (String s : toRemove) {
/*  896 */       logger.info("Removing Card " + s + " for trailer build.");
/*  897 */       cards.remove(s);
/*      */     } 
/*  899 */     toRemove.clear();
/*      */     
/*  901 */     for (Map.Entry<String, AbstractCard> c : curses.entrySet()) {
/*  902 */       if (((AbstractCard)c.getValue()).assetUrl == null) {
/*  903 */         toRemove.add(c.getKey());
/*      */       }
/*      */     } 
/*      */     
/*  907 */     for (String s : toRemove) {
/*  908 */       logger.info("Removing Curse " + s + " for trailer build.");
/*  909 */       curses.remove(s);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void unlockAndSeeAllCards() {
/*  918 */     for (String s : UnlockTracker.lockedCards) {
/*  919 */       UnlockTracker.hardUnlockOverride(s);
/*      */     }
/*      */ 
/*      */     
/*  923 */     for (Map.Entry<String, AbstractCard> c : cards.entrySet()) {
/*  924 */       if (((AbstractCard)c.getValue()).rarity != AbstractCard.CardRarity.BASIC && 
/*  925 */         !UnlockTracker.isCardSeen(c.getKey())) {
/*  926 */         UnlockTracker.markCardAsSeen(c.getKey());
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  931 */     for (Map.Entry<String, AbstractCard> c : curses.entrySet()) {
/*  932 */       if (!UnlockTracker.isCardSeen(c.getKey())) {
/*  933 */         UnlockTracker.markCardAsSeen(c.getKey());
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void add(AbstractCard card) {
/*  944 */     switch (card.color) {
/*      */       case COLORLESS:
/*  946 */         redCards++;
/*  947 */         if (UnlockTracker.isCardSeen(card.cardID)) {
/*  948 */           seenRedCards++;
/*      */         }
/*      */         break;
/*      */       case CURSE:
/*  952 */         greenCards++;
/*  953 */         if (UnlockTracker.isCardSeen(card.cardID)) {
/*  954 */           seenGreenCards++;
/*      */         }
/*      */         break;
/*      */       case RED:
/*  958 */         purpleCards++;
/*  959 */         if (UnlockTracker.isCardSeen(card.cardID)) {
/*  960 */           seenPurpleCards++;
/*      */         }
/*      */         break;
/*      */       case GREEN:
/*  964 */         blueCards++;
/*  965 */         if (UnlockTracker.isCardSeen(card.cardID)) {
/*  966 */           seenBlueCards++;
/*      */         }
/*      */         break;
/*      */       case BLUE:
/*  970 */         colorlessCards++;
/*  971 */         if (UnlockTracker.isCardSeen(card.cardID)) {
/*  972 */           seenColorlessCards++;
/*      */         }
/*      */         break;
/*      */       case PURPLE:
/*  976 */         curseCards++;
/*  977 */         if (UnlockTracker.isCardSeen(card.cardID)) {
/*  978 */           seenCurseCards++;
/*      */         }
/*  980 */         curses.put(card.cardID, card);
/*      */         break;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  986 */     if (!UnlockTracker.isCardSeen(card.cardID)) {
/*  987 */       card.isSeen = false;
/*      */     }
/*  989 */     cards.put(card.cardID, card);
/*  990 */     totalCardCount++;
/*      */   }
/*      */   
/*      */   public static AbstractCard getCopy(String key, int upgradeTime, int misc) {
/*  994 */     AbstractCard source = getCard(key);
/*  995 */     AbstractCard retVal = null;
/*      */     
/*  997 */     if (source == null) {
/*  998 */       retVal = getCard("Madness").makeCopy();
/*      */     } else {
/* 1000 */       retVal = getCard(key).makeCopy();
/*      */     } 
/*      */     
/* 1003 */     for (int i = 0; i < upgradeTime; i++) {
/* 1004 */       retVal.upgrade();
/*      */     }
/*      */     
/* 1007 */     retVal.misc = misc;
/* 1008 */     if (misc != 0) {
/* 1009 */       if (retVal.cardID.equals("Genetic Algorithm")) {
/* 1010 */         retVal.block = misc;
/* 1011 */         retVal.baseBlock = misc;
/* 1012 */         retVal.initializeDescription();
/*      */       } 
/* 1014 */       if (retVal.cardID.equals("RitualDagger")) {
/* 1015 */         retVal.damage = misc;
/* 1016 */         retVal.baseDamage = misc;
/* 1017 */         retVal.initializeDescription();
/*      */       } 
/*      */     } 
/* 1020 */     return retVal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static AbstractCard getCopy(String key) {
/* 1030 */     return getCard(key).makeCopy();
/*      */   }
/*      */   
/*      */   public static AbstractCard getCard(AbstractPlayer.PlayerClass plyrClass, String key) {
/* 1034 */     return cards.get(key);
/*      */   }
/*      */   
/*      */   public static AbstractCard getCard(String key) {
/* 1038 */     return cards.get(key);
/*      */   }
/*      */   
/*      */   public static String getCardNameFromMetricID(String metricID) {
/* 1042 */     String[] components = metricID.split("\\+");
/*      */     
/* 1044 */     String baseId = components[0];
/* 1045 */     AbstractCard card = cards.getOrDefault(baseId, null);
/* 1046 */     if (card == null) {
/* 1047 */       return metricID;
/*      */     }
/*      */     try {
/* 1050 */       if (components.length > 1) {
/* 1051 */         card = card.makeCopy();
/* 1052 */         int upgrades = Integer.parseInt(components[1]);
/* 1053 */         for (int i = 0; i < upgrades; i++) {
/* 1054 */           card.upgrade();
/*      */         }
/*      */       } 
/* 1057 */     } catch (IndexOutOfBoundsException|NumberFormatException indexOutOfBoundsException) {}
/*      */ 
/*      */     
/* 1060 */     return card.name;
/*      */   }
/*      */   
/*      */   public static boolean isACard(String metricID) {
/* 1064 */     String[] components = metricID.split("\\+");
/* 1065 */     String baseId = components[0];
/* 1066 */     AbstractCard card = cards.getOrDefault(baseId, null);
/* 1067 */     return (card != null);
/*      */   }
/*      */   
/*      */   public static AbstractCard getCurse() {
/* 1071 */     ArrayList<String> tmp = new ArrayList<>();
/* 1072 */     for (Map.Entry<String, AbstractCard> c : curses.entrySet()) {
/* 1073 */       if (!((AbstractCard)c.getValue()).cardID.equals("AscendersBane") && !((AbstractCard)c.getValue()).cardID.equals("Necronomicurse") && 
/* 1074 */         !((AbstractCard)c.getValue()).cardID.equals("CurseOfTheBell") && !((AbstractCard)c.getValue()).cardID.equals("Pride")) {
/* 1075 */         tmp.add(c.getKey());
/*      */       }
/*      */     } 
/*      */     
/* 1079 */     return cards.get(tmp.get(AbstractDungeon.cardRng.random(0, tmp.size() - 1)));
/*      */   }
/*      */   
/*      */   public static AbstractCard getCurse(AbstractCard prohibitedCard, Random rng) {
/* 1083 */     ArrayList<String> tmp = new ArrayList<>();
/* 1084 */     for (Map.Entry<String, AbstractCard> c : curses.entrySet()) {
/* 1085 */       if (!Objects.equals(((AbstractCard)c.getValue()).cardID, prohibitedCard.cardID) && !Objects.equals(((AbstractCard)c
/* 1086 */           .getValue()).cardID, "Necronomicurse") && 
/* 1087 */         !Objects.equals(((AbstractCard)c.getValue()).cardID, "AscendersBane") && !Objects.equals(((AbstractCard)c
/* 1088 */           .getValue()).cardID, "CurseOfTheBell") && 
/* 1089 */         !Objects.equals(((AbstractCard)c.getValue()).cardID, "Pride"))
/*      */       {
/* 1091 */         tmp.add(c.getKey());
/*      */       }
/*      */     } 
/*      */     
/* 1095 */     return cards.get(tmp.get(rng.random(0, tmp.size() - 1)));
/*      */   }
/*      */   
/*      */   public static AbstractCard getCurse(AbstractCard prohibitedCard) {
/* 1099 */     return getCurse(prohibitedCard, new Random());
/*      */   }
/*      */   
/*      */   public static void uploadCardData() {
/* 1103 */     ArrayList<String> data = new ArrayList<>();
/*      */     
/* 1105 */     for (Map.Entry<String, AbstractCard> c : cards.entrySet()) {
/* 1106 */       data.add(((AbstractCard)c.getValue()).gameDataUploadData());
/* 1107 */       AbstractCard c2 = ((AbstractCard)c.getValue()).makeCopy();
/* 1108 */       if (c2.canUpgrade()) {
/* 1109 */         c2.upgrade();
/* 1110 */         data.add(c2.gameDataUploadData());
/*      */       } 
/*      */     } 
/* 1113 */     BotDataUploader.uploadDataAsync(BotDataUploader.GameDataType.CARD_DATA, AbstractCard.gameDataUploadHeader(), data);
/*      */   }
/*      */   
/*      */   public static ArrayList<AbstractCard> getAllCards() {
/* 1117 */     ArrayList<AbstractCard> retVal = new ArrayList<>();
/* 1118 */     for (Map.Entry<String, AbstractCard> c : cards.entrySet()) {
/* 1119 */       retVal.add(c.getValue());
/*      */     }
/* 1121 */     return retVal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static AbstractCard getAnyColorCard(AbstractCard.CardType type, AbstractCard.CardRarity rarity) {
/* 1128 */     CardGroup anyCard = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
/*      */     
/* 1130 */     for (Map.Entry<String, AbstractCard> c : cards.entrySet()) {
/* 1131 */       if (((AbstractCard)c.getValue()).rarity == rarity && !((AbstractCard)c.getValue()).hasTag(AbstractCard.CardTags.HEALING) && ((AbstractCard)c
/* 1132 */         .getValue()).type != AbstractCard.CardType.CURSE && ((AbstractCard)c.getValue()).type != AbstractCard.CardType.STATUS && ((AbstractCard)c
/* 1133 */         .getValue()).type == type && (!UnlockTracker.isCardLocked(c.getKey()) || 
/* 1134 */         Settings.treatEverythingAsUnlocked())) {
/* 1135 */         anyCard.addToBottom(c.getValue());
/*      */       }
/*      */     } 
/*      */     
/* 1139 */     anyCard.shuffle(AbstractDungeon.cardRandomRng);
/* 1140 */     return anyCard.getRandomCard(true, rarity);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static AbstractCard getAnyColorCard(AbstractCard.CardRarity rarity) {
/* 1147 */     CardGroup anyCard = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
/*      */     
/* 1149 */     for (Map.Entry<String, AbstractCard> c : cards.entrySet()) {
/* 1150 */       if (((AbstractCard)c.getValue()).rarity == rarity && ((AbstractCard)c.getValue()).type != AbstractCard.CardType.CURSE && ((AbstractCard)c
/* 1151 */         .getValue()).type != AbstractCard.CardType.STATUS && (!UnlockTracker.isCardLocked(c.getKey()) || 
/* 1152 */         Settings.treatEverythingAsUnlocked())) {
/* 1153 */         anyCard.addToBottom(c.getValue());
/*      */       }
/*      */     } 
/*      */     
/* 1157 */     anyCard.shuffle(AbstractDungeon.cardRng);
/* 1158 */     return anyCard.getRandomCard(true, rarity).makeCopy();
/*      */   }
/*      */   
/*      */   public static CardGroup getEachRare(AbstractPlayer p) {
/* 1162 */     CardGroup everyRareCard = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
/* 1163 */     for (Map.Entry<String, AbstractCard> c : cards.entrySet()) {
/* 1164 */       if (((AbstractCard)c.getValue()).color == p.getCardColor() && ((AbstractCard)c.getValue()).rarity == AbstractCard.CardRarity.RARE) {
/* 1165 */         everyRareCard.addToBottom(((AbstractCard)c.getValue()).makeCopy());
/*      */       }
/*      */     } 
/* 1168 */     return everyRareCard;
/*      */   }
/*      */   
/*      */   public static ArrayList<AbstractCard> getCardList(LibraryType type) {
/* 1172 */     ArrayList<AbstractCard> retVal = new ArrayList<>();
/* 1173 */     switch (type) {
/*      */       case COLORLESS:
/* 1175 */         for (Map.Entry<String, AbstractCard> c : cards.entrySet()) {
/* 1176 */           if (((AbstractCard)c.getValue()).color == AbstractCard.CardColor.COLORLESS) {
/* 1177 */             retVal.add(c.getValue());
/*      */           }
/*      */         } 
/*      */         break;
/*      */       case CURSE:
/* 1182 */         for (Map.Entry<String, AbstractCard> c : cards.entrySet()) {
/* 1183 */           if (((AbstractCard)c.getValue()).color == AbstractCard.CardColor.CURSE) {
/* 1184 */             retVal.add(c.getValue());
/*      */           }
/*      */         } 
/*      */         break;
/*      */       case RED:
/* 1189 */         for (Map.Entry<String, AbstractCard> c : cards.entrySet()) {
/* 1190 */           if (((AbstractCard)c.getValue()).color == AbstractCard.CardColor.RED) {
/* 1191 */             retVal.add(c.getValue());
/*      */           }
/*      */         } 
/*      */         break;
/*      */       case GREEN:
/* 1196 */         for (Map.Entry<String, AbstractCard> c : cards.entrySet()) {
/* 1197 */           if (((AbstractCard)c.getValue()).color == AbstractCard.CardColor.GREEN) {
/* 1198 */             retVal.add(c.getValue());
/*      */           }
/*      */         } 
/*      */         break;
/*      */       case BLUE:
/* 1203 */         for (Map.Entry<String, AbstractCard> c : cards.entrySet()) {
/* 1204 */           if (((AbstractCard)c.getValue()).color == AbstractCard.CardColor.BLUE) {
/* 1205 */             retVal.add(c.getValue());
/*      */           }
/*      */         } 
/*      */         break;
/*      */       case PURPLE:
/* 1210 */         for (Map.Entry<String, AbstractCard> c : cards.entrySet()) {
/* 1211 */           if (((AbstractCard)c.getValue()).color == AbstractCard.CardColor.PURPLE) {
/* 1212 */             retVal.add(c.getValue());
/*      */           }
/*      */         } 
/*      */         break;
/*      */     } 
/*      */ 
/*      */     
/* 1219 */     return retVal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void addCardsIntoPool(ArrayList<AbstractCard> tmpPool, AbstractCard.CardColor color) {
/* 1229 */     logger.info("[INFO] Adding " + color + " cards into card pool.");
/* 1230 */     AbstractCard card = null;
/* 1231 */     for (Map.Entry<String, AbstractCard> c : cards.entrySet()) {
/* 1232 */       card = c.getValue();
/* 1233 */       if (card.color == color && card.rarity != AbstractCard.CardRarity.BASIC && card.type != AbstractCard.CardType.STATUS && (
/* 1234 */         !UnlockTracker.isCardLocked(c.getKey()) || Settings.treatEverythingAsUnlocked())) {
/* 1235 */         tmpPool.add(card);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void addRedCards(ArrayList<AbstractCard> tmpPool) {
/* 1246 */     logger.info("[INFO] Adding red cards into card pool.");
/* 1247 */     AbstractCard card = null;
/* 1248 */     for (Map.Entry<String, AbstractCard> c : cards.entrySet()) {
/* 1249 */       card = c.getValue();
/* 1250 */       if (card.color == AbstractCard.CardColor.RED && card.rarity != AbstractCard.CardRarity.BASIC && (
/* 1251 */         !UnlockTracker.isCardLocked(c.getKey()) || Settings.treatEverythingAsUnlocked())) {
/* 1252 */         tmpPool.add(card);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void addGreenCards(ArrayList<AbstractCard> tmpPool) {
/* 1264 */     logger.info("[INFO] Adding green cards into card pool.");
/* 1265 */     AbstractCard card = null;
/* 1266 */     for (Map.Entry<String, AbstractCard> c : cards.entrySet()) {
/* 1267 */       card = c.getValue();
/* 1268 */       if (card.color == AbstractCard.CardColor.GREEN && card.rarity != AbstractCard.CardRarity.BASIC && (
/* 1269 */         !UnlockTracker.isCardLocked(c.getKey()) || Settings.treatEverythingAsUnlocked())) {
/* 1270 */         tmpPool.add(card);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void addBlueCards(ArrayList<AbstractCard> tmpPool) {
/* 1282 */     logger.info("[INFO] Adding blue cards into card pool.");
/* 1283 */     AbstractCard card = null;
/* 1284 */     for (Map.Entry<String, AbstractCard> c : cards.entrySet()) {
/* 1285 */       card = c.getValue();
/* 1286 */       if (card.color == AbstractCard.CardColor.BLUE && card.rarity != AbstractCard.CardRarity.BASIC && (
/* 1287 */         !UnlockTracker.isCardLocked(c.getKey()) || Settings.treatEverythingAsUnlocked())) {
/* 1288 */         tmpPool.add(card);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void addPurpleCards(ArrayList<AbstractCard> tmpPool) {
/* 1295 */     logger.info("[INFO] Adding purple cards into card pool.");
/* 1296 */     AbstractCard card = null;
/* 1297 */     for (Map.Entry<String, AbstractCard> c : cards.entrySet()) {
/* 1298 */       card = c.getValue();
/* 1299 */       if (card.color == AbstractCard.CardColor.PURPLE && card.rarity != AbstractCard.CardRarity.BASIC && (
/* 1300 */         !UnlockTracker.isCardLocked(c.getKey()) || Settings.treatEverythingAsUnlocked())) {
/* 1301 */         tmpPool.add(card);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void addColorlessCards(ArrayList<AbstractCard> tmpPool) {
/* 1313 */     logger.info("[INFO] Adding colorless cards into card pool.");
/* 1314 */     AbstractCard card = null;
/* 1315 */     for (Map.Entry<String, AbstractCard> c : cards.entrySet()) {
/* 1316 */       card = c.getValue();
/* 1317 */       if (card.color == AbstractCard.CardColor.COLORLESS && card.type != AbstractCard.CardType.STATUS && (
/* 1318 */         !UnlockTracker.isCardLocked(c.getKey()) || Settings.treatEverythingAsUnlocked()))
/* 1319 */         tmpPool.add(card); 
/*      */     } 
/*      */   }
/*      */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\helpers\CardLibrary.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */