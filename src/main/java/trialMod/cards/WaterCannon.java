package trialMod.cards;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.optionCards.BecomeAlmighty;
import com.megacrit.cardcrawl.cards.optionCards.FameAndFortune;
import com.megacrit.cardcrawl.cards.optionCards.LiveForever;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import trialMod.Characters.Roxy;
import trialMod.actions.WaterCannonAction;
import trialMod.actions.killAndAmplifyMagicAction;
import trialMod.modcore.MushokuTensei;
import trialMod.powers.IceChant;

import java.util.ArrayList;
import java.util.Iterator;

public class WaterCannon extends CustomCard {
    public static final String ID = "TrialMod:WaterCannon";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "trialModResources/img/cards/Strike.png";
    private static final int COST = 0;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = Roxy.Enums.ROXY_CARD;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.NONE;

    private static final int chantCost = 2;

    public AbstractCard constPrev1 = new WaterCannonSeparate();

    public AbstractCard constPrev2 = new WaterCannonSingle();

    public AbstractCard prev1;

    public AbstractCard prev2;


    public WaterCannon() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.tags.add(MushokuTensei.MAGIC);
        this.tags.add(MushokuTensei.MAGIC_ATTACK);
        this.prev1 = this.constPrev1;
        this.prev2 = this.constPrev2;
        if (this.upgraded) {
            this.prev1.upgrade();
            this.prev2.upgrade();
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();

            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
            this.constPrev1.upgrade();
            this.constPrev2.upgrade();
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        AbstractPlayer player = AbstractDungeon.player;
        if (player instanceof Roxy && ((Roxy) (player)).iceCount >= chantCost) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }

    }

    @Override
    public void applyPowers() {}

    @Override
    public void calculateCardDamage(AbstractMonster mo) {}
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        //System.out.printf("In judge:%d, %d%n",((Roxy) (p)).iceCount,chantCost);
        boolean canUse = super.canUse(p, m);
        if (!canUse)
            return false;
        if (((Roxy) p).iceCount < chantCost) {
            this.cantUseMessage = CARD_STRINGS.EXTENDED_DESCRIPTION[0];
            return false;
        }
        return canUse;
    }

    public void hover() {
        this.prev1 = this.constPrev1;
        this.prev2 = this.constPrev2;
        super.hover();
    }

    public void unhover() {
        super.unhover();
        this.prev1 = null;
        this.prev2 = null;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new IceChant(p,-chantCost), -chantCost));
        addToBot(new WaterCannonAction(this.upgraded));

    }


}
