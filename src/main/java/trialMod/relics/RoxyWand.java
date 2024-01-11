package trialMod.relics;

import basemod.abstracts.CustomRelic;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import trialMod.Characters.Roxy;

public class RoxyWand extends CustomRelic implements ClickableRelic {
    // 遗物ID
    public static final String ID = "trialMod:RoxyWand";
    private int counter = 0;
    // 图片路径
    private static final String IMG_PATH = "trialModResources/img/relics/roxy_wand.png";
    // 遗物类型
    private static final RelicTier RELIC_TIER = RelicTier.STARTER;
    // 点击音效
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;

    public RoxyWand() {
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
    }

    // 获取遗物描述，但原版游戏只在初始化和获取遗物时调用，故该方法等于初始描述
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new RoxyWand();
    }

    @Override
    public void atTurnStart() {
        counter = 0;
    }


    @Override
    public void onRightClick() {
        //todo: 改成释放魔法
        flash();
        addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature) AbstractDungeon.player, this));
        AbstractPlayer player = AbstractDungeon.player;
        if (player instanceof Roxy) {
            Roxy RoxyPlayer = (Roxy)player;
            addToBot((AbstractGameAction)new TalkAction(true, String.format("我当前的吟唱层数为 %d!",RoxyPlayer.chantCount), 1.0F, 2.0F));
            if (counter<2){
                boolean isReadyToChant = ((Roxy) player).isReadyToChant;
                if (isReadyToChant){
                    this.addToBot(new DrawCardAction(1));
                    counter++;
                }else{
                    addToBot((AbstractGameAction)new TalkAction(true, "我还未准备好释放魔法！", 1.0F, 2.0F));
                }

            }else{
                addToBot((AbstractGameAction)new TalkAction(true, "我已经不能释放魔法了！", 1.0F, 2.0F));
            }
        }else {
            addToBot((AbstractGameAction)new TalkAction(true, "你不是洛琪希，你无权使用我！", 1.0F, 2.0F));
        }



    }


}