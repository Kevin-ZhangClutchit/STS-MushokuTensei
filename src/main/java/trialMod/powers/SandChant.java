package trialMod.powers;


import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import trialMod.Characters.Roxy;

public class SandChant extends AbstractPower {
    public static final String POWER_ID = "TrialMod:SandChant";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public SandChant(AbstractCreature owner, int Amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;

        // 如果需要不能叠加的能力，只需将上面的Amount参数删掉，并把下面的Amount改成-1就行
        this.amount = Amount;
        if (this.owner instanceof Roxy){
            ((Roxy)owner).chantCount += Amount;
            ((Roxy)owner).isReadyToChant= ((Roxy) owner).chantCount >= 3;
        }
        // 添加一大一小两张能力图
        String path128 = "trialModResources/img/powers/Example84.png";
        String path48 = "trialModResources/img/powers/Example32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        // 首次添加能力更新描述
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], this.amount);
    }
    @Override
    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }
    @Override
    public void reducePower(int reduceAmount) {
        if (this.amount - reduceAmount <= 0) {
            this.fontScale = 8.0F;
            this.amount = 0;
            if (this.owner instanceof Roxy){
                ((Roxy)owner).chantCount = 0;
                ((Roxy)owner).isReadyToChant= ((Roxy) owner).chantCount >= 3;
            }
        } else {
            this.fontScale = 8.0F;
            this.amount -= reduceAmount;
            if (this.owner instanceof Roxy){
                ((Roxy)owner).chantCount -= reduceAmount;
                ((Roxy)owner).isReadyToChant= ((Roxy) owner).chantCount >= 3;
            }
        }


    }
    public int onAttacked(DamageInfo info, int damageAmount) {
        //todo: add effect
        if (info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != null && info.owner != this.owner && damageAmount > 0) {
            this.flash();
        }
        return damageAmount;

    }
}
