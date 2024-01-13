package trialMod.modcore;

import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;

import basemod.BaseMod;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import trialMod.Characters.Roxy;
import trialMod.cards.*;
import com.badlogic.gdx.graphics.Color;
import trialMod.relics.MyRelic;
import trialMod.relics.RoxyWand;

import static trialMod.Characters.Roxy.Enums.*;

@SpireInitializer
public class MushokuTensei implements EditCardsSubscriber, EditStringsSubscriber, EditCharactersSubscriber,
        EditRelicsSubscriber, EditKeywordsSubscriber {
    private static final String ROXY_BUTTON = "trialModResources/img/char/Roxy_Character_Button.png";
    private static final String ROXY_PORTRAIT = "trialModResources/img/char/Roxy_Portrait.jpg";
    private static final String ROXY_ATTACK_512 = "trialModResources/img/512/roxy_attack_512.png";
    private static final String ROXY_POWER_512 = "trialModResources/img/512/roxy_power_512.png";
    private static final String ROXY_SKILL_512 = "trialModResources/img/512/roxy_skill_512.png";
    private static final String SMALL_ORB = "trialModResources/img/char/small_orb.png";
    private static final String ROXY_ATTACK_1024 = "trialModResources/img/1024/roxy_attack.png";
    private static final String ROXY_POWER_1024 = "trialModResources/img/1024/roxy_power.png";
    private static final String ROXY_SKILL_1024 = "trialModResources/img/1024/roxy_skill.png";
    private static final String BIG_ORB = "trialModResources/img/char/card_orb.png";
    private static final String ENEYGY_ORB = "trialModResources/img/char/cost_orb.png";
    public static final Color ROXY_BLUE
            = new Color(109.0F / 255.0F, 122.0F / 255.0F, 181.0F / 255.0F, 1.0F);


    // new card tag
    @SpireEnum
    public static AbstractCard.CardTags MAGIC; //all magic cards
    @SpireEnum
    public static AbstractCard.CardTags MAGIC_ATTACK;


    public MushokuTensei() {
        BaseMod.subscribe(this);
        BaseMod.addColor(ROXY_CARD, ROXY_BLUE, ROXY_BLUE, ROXY_BLUE, ROXY_BLUE, ROXY_BLUE, ROXY_BLUE, ROXY_BLUE,ROXY_ATTACK_512,ROXY_SKILL_512,ROXY_POWER_512,ENEYGY_ORB,ROXY_ATTACK_1024,ROXY_SKILL_1024,ROXY_POWER_1024,BIG_ORB,SMALL_ORB);
    }

    
    public static void initialize() {
        new MushokuTensei();
    }

    @Override
    public void receiveEditCards() {
        BaseMod.addCard(new Strike());
        BaseMod.addCard(new MagicStrike());
        BaseMod.addCard(new MagicDefend());
        BaseMod.addCard(new ChantIce());
        BaseMod.addCard(new ChantFire());
        BaseMod.addCard(new ChantSand());
        BaseMod.addCard(new WaterBall());
        BaseMod.addCard(new WaterShield());
        BaseMod.addCard(new IceSmash());
    }


    public void receiveEditStrings() {
        String lang;
        if (Settings.language == Settings.GameLanguage.ZHS) {
            lang = "ZHS";
        } else {
            lang = "ENG";
        }
        BaseMod.loadCustomStringsFile(CardStrings.class, "trialModResources/localization/" + lang + "/cards.json"); // 加载相应语言的卡牌本地化内容。
        BaseMod.loadCustomStringsFile(CharacterStrings.class, "trialModResources/localization/" + lang + "/characters.json");
        BaseMod.loadCustomStringsFile(RelicStrings.class, "trialModResources/localization/" + lang + "/relics.json");
        BaseMod.loadCustomStringsFile(PowerStrings.class, "trialModResources/localization/" + lang + "/powers.json");
    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new Roxy(CardCrawlGame.playerName), ROXY_BUTTON, ROXY_PORTRAIT, ROXY_CHARACTER);
    }

    @Override
    public void receiveEditRelics() {
        //BaseMod.addRelic(new MyRelic(), RelicType.SHARED);
        BaseMod.addRelic(new RoxyWand(), RelicType.SHARED);

    }

    @Override
    public void receiveEditKeywords() {
        BaseMod.addKeyword("trialmod", "初级", new String[] {"初级"}, "最基础的入门魔法，即使是剑士也可以习得 #y初级 魔法。");
        BaseMod.addKeyword("trialmod", "中级", new String[] {"中级"}, " #y中级 魔法。");
        BaseMod.addKeyword("trialmod", "上级", new String[] {"上级"}, " #y上级 魔法。");
        BaseMod.addKeyword("trialmod", "圣级", new String[] {"圣级"}, " #y圣级 魔法。");
        BaseMod.addKeyword("trialmod", "王级", new String[] {"王级"}, " #y王级 魔法。");
        BaseMod.addKeyword("trialmod", "帝级", new String[] {"帝级"}, " #y帝级 魔法。");
        BaseMod.addKeyword("trialmod", "吟唱", new String[] {"吟唱"}, "在鲁迪乌斯推广无吟唱魔法前， #y吟唱 被认为是魔法的必要步骤。");
        BaseMod.addKeyword("trialmod", "魔力增幅", new String[] {"魔力增幅"}, " #y魔力增幅 会提升你魔法攻击的伤害。");
    }
}