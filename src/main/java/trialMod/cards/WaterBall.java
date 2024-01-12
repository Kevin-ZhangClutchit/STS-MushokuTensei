package trialMod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import trialMod.Characters.Roxy;
import trialMod.powers.IceChant;

public class WaterBall extends CustomCard{
    public static final String ID = "TrialMod:WaterBall";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "trialModResources/img/cards/Strike.png";
    private static final int COST = 0;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = Roxy.Enums.ROXY_CARD;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public WaterBall() {
        super (ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = 8;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(2);
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        AbstractPlayer player = AbstractDungeon.player;
        if (player instanceof Roxy && ((Roxy)(player)).iceCount >= this.magicNumber){
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }

    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse)
            return false;
        if ((!(p instanceof Roxy)) || ((Roxy)(p)).iceCount < this.magicNumber) {
            this.cantUseMessage = CARD_STRINGS.EXTENDED_DESCRIPTION[0];
            return false;
        }
        return canUse;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL)));
        this.addToBot(new ApplyPowerAction(p, p, new IceChant(p,-this.magicNumber), -this.magicNumber));
    }

}
