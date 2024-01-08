package trialMod.modcore;

import basemod.helpers.RelicType;
import basemod.interfaces.EditCharactersSubscriber;
import basemod.interfaces.EditRelicsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;

import basemod.BaseMod;
import basemod.interfaces.EditCardsSubscriber;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import trialMod.Characters.MyCharacter;
import trialMod.cards.Strike;
import com.badlogic.gdx.graphics.Color;
import trialMod.relics.MyRelic;

import static trialMod.Characters.MyCharacter.Enums.EXAMPLE_CARD;
import static trialMod.Characters.MyCharacter.Enums.MY_CHARACTER;

@SpireInitializer
public class MushokuTensei implements EditCardsSubscriber, EditStringsSubscriber, EditCharactersSubscriber,
        EditRelicsSubscriber {
    private static final String MY_CHARACTER_BUTTON = "trialModResources/img/char/Character_Button.png";
    private static final String MY_CHARACTER_PORTRAIT = "trialModResources/img/char/Character_Portrait.png";
    private static final String BG_ATTACK_512 = "trialModResources/img/512/bg_attack_512.png";
    private static final String BG_POWER_512 = "trialModResources/img/512/bg_power_512.png";
    private static final String BG_SKILL_512 = "trialModResources/img/512/bg_skill_512.png";
    private static final String SMALL_ORB = "trialModResources/img/char/small_orb.png";
    private static final String BG_ATTACK_1024 = "trialModResources/img/1024/bg_attack.png";
    private static final String BG_POWER_1024 = "trialModResources/img/1024/bg_power.png";
    private static final String BG_SKILL_1024 = "trialModResources/img/1024/bg_skill.png";
    private static final String BIG_ORB = "trialModResources/img/char/card_orb.png";
    private static final String ENEYGY_ORB = "trialModResources/img/char/cost_orb.png";
    public static final Color ROXY_BLUE
            = new Color(109.0F / 255.0F, 122.0F / 255.0F, 181.0F / 255.0F, 1.0F);
    public MushokuTensei() {
        BaseMod.subscribe(this);
        BaseMod.addColor(EXAMPLE_CARD, ROXY_BLUE, ROXY_BLUE, ROXY_BLUE, ROXY_BLUE, ROXY_BLUE, ROXY_BLUE, ROXY_BLUE,BG_ATTACK_512,BG_SKILL_512,BG_POWER_512,ENEYGY_ORB,BG_ATTACK_1024,BG_SKILL_1024,BG_POWER_1024,BIG_ORB,SMALL_ORB);
    }

    
    public static void initialize() {
        new MushokuTensei();
    }

    @Override
    public void receiveEditCards() {
        BaseMod.addCard(new Strike());
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
    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new MyCharacter(CardCrawlGame.playerName), MY_CHARACTER_BUTTON, MY_CHARACTER_PORTRAIT, MY_CHARACTER);
    }

    @Override
    public void receiveEditRelics() {
        BaseMod.addRelic(new MyRelic(), RelicType.SHARED);
    }
}