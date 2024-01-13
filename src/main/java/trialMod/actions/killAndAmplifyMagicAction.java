package trialMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import trialMod.powers.MagicAmplify;

public class killAndAmplifyMagicAction extends AbstractGameAction {

    public DamageInfo info;
    public int magicNumber;


    public killAndAmplifyMagicAction(AbstractMonster target, DamageInfo info, int magicNumber) {
        this.target = target;
        this.info = info;
        this.magicNumber = magicNumber;
    }

    @Override
    public void update() {
        this.target.damage(this.info);
        if ((this.target.isDying || this.target.currentHealth <= 0) && !this.target.halfDead
                && !this.target.hasPower("Minion")) {
            AbstractPlayer p = AbstractDungeon.player;
            this.addToTop(new ApplyPowerAction(p, p, new MagicAmplify(p,this.magicNumber), this.magicNumber));
        }
        this.isDone = true;
    }
}
