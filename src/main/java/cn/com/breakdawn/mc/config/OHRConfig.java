package cn.com.breakdawn.mc.config;

import cn.com.breakdawn.mc.OceanHeartR;
import net.minecraftforge.common.config.Config;

/**
 * @author KSGFK
 */
@Config(modid = OceanHeartR.MODID)
@Config.LangKey("oceanheartr.config")
public final class OHRConfig {
    @Config.Name("General")
    @Config.LangKey("oceanheartr.config.general")
    public static final General general = new General();

    public static final class General {
        @Config.Comment("试作型自然发电机一式最大能量储存")
        @Config.LangKey("oceanheartr.config.general.dynNature.maxEnergy")
        @Config.Name("dynNatureMaxEnergy")
        public int dynNatureMaxEnergy = 32000000;

        @Config.Comment("试作型自然发电机一式最大能量发送")
        @Config.LangKey("oceanheartr.config.general.dynNature.maxExtract")
        @Config.Name("dynNatureMaxExtract")
        public int dynNatureMaxExtract = 1000;

        @Config.Comment("试作型自然发电机一式最大能量生产速度(RF/t)")
        @Config.LangKey("oceanheartr.config.general.dynNature.maxReceive")
        @Config.Name("dynNatureMaxReceive")
        public int dynNatureMaxReceive = 1000;

        @Config.Comment("试作型自然发电机一式每一枚结晶最大发电量")
        @Config.LangKey("oceanheartr.config.general.dynNature.perGen")
        @Config.Name("dynNaturePerGen")
        public int dynNaturePerGen = 1000000;

        @Config.Comment("试作型自然发电机一式是否可以放入地狱自然结晶")
        @Config.LangKey("oceanheartr.config.general.dynNature.canPut")
        @Config.Name("dynNatureCanPut")
        public boolean dynNatureCanPut = false;

        @Config.Comment("世界树是否可以生长")
        @Config.LangKey("oceanheartr.config.general.yggdrasill.grow")
        @Config.Name("canYggdrasillGrow")
        public boolean canYggdrasillGrow = true;

        @Config.Comment("打粉机最大能量储存")
        @Config.LangKey("oceanheartr.config.general.pul.maxEnergy")
        @Config.Name("pulMaxEnergy")
        public int pulMaxEnergy = 320000;

        @Config.Comment("打粉机最大能量接收")
        @Config.LangKey("oceanheartr.config.general.pul.maxReceive")
        @Config.Name("pulMaxReceive")
        public int pulMaxReceive = 80;

        @Config.Comment("打粉机最大能量消耗,处理1个矿石消耗能量等于pulPerGenTime * pulMaxExtract")
        @Config.LangKey("oceanheartr.config.general.pul.maxExtract")
        @Config.Name("pulMaxExtract")
        public int pulMaxExtract = 10;

        @Config.Comment("打粉机处理1个矿石时间,处理1个矿石消耗能量等于pulPerGenTime * pulMaxExtract")
        @Config.LangKey("oceanheartr.config.general.pul.perGen")
        @Config.Name("pulPerGenTime")
        public int pulPerGenTime = 100;

        @Config.Comment("自然之剑是否可以攻击动物")
        @Config.LangKey("oceanheartr.config.general.natureSworld.canHitAnimal")
        @Config.Name("natureSwordCanHitAnimal")
        public boolean natureSwordCanHitAnimal = false;

        @Config.Comment("自然之剑是否可以在PVP时回血")
        @Config.LangKey("oceanheartr.config.general.natureSworld.pvp")
        @Config.Name("natureSwordPVP")
        public int natureSwordPVP = 5;

        @Config.Comment("自然之剑是否可以在攻击动物时回血")
        @Config.LangKey("oceanheartr.config.general.natureSworld.mob")
        @Config.Name("natureSwordMob")
        public float natureSwordMob = 0.25f;

        @Config.Comment("弗拉德三世技能CD")
        @Config.LangKey("oceanheartr.config.general.vladiii.cd")
        @Config.Name("vladIIIMaxCD")
        public int vladIIIMaxCD = 300;

        @Config.Comment("净化炉最大能量储存")
        @Config.LangKey("oceanheartr.config.general.pur.maxEnergy")
        @Config.Name("purMaxEnergy")
        public int purMaxEnergy = 320000;

        @Config.Comment("净化炉最大能量接收")
        @Config.LangKey("oceanheartr.config.general.pur.maxReceive")
        @Config.Name("purMaxReceive")
        public int purMaxReceive = 80;

        @Config.Comment("净化炉最大能量消耗,处理1个矿石消耗能量等于purPerGenTime * purMaxExtract")
        @Config.LangKey("oceanheartr.config.general.pur.maxExtract")
        @Config.Name("purMaxExtract")
        public int purMaxExtract = 10;

        @Config.Comment("净化炉处理1个矿石时间,处理1个矿石消耗能量等于purPerGenTime * purMaxExtract")
        @Config.LangKey("oceanheartr.config.general.pur.perGen")
        @Config.Name("purPerGenTime")
        public int purPerGenTime = 100;

        @Config.Comment("净化炉最大能量储存")
        @Config.LangKey("oceanheartr.config.general.phi.maxEnergy")
        @Config.Name("purMaxEnergy")
        public int phiMaxEnergy = 320000;

        @Config.Comment("净化炉最大能量接收")
        @Config.LangKey("oceanheartr.config.general.phi.maxReceive")
        @Config.Name("purMaxReceive")
        public int phiMaxReceive = 80;

        @Config.Comment("净化炉最大能量消耗,处理1个矿石消耗能量等于purPerGenTime * purMaxExtract")
        @Config.LangKey("oceanheartr.config.general.phi.maxExtract")
        @Config.Name("purMaxExtract")
        public int phiMaxExtract = 10;

        @Config.Comment("净化炉处理1个矿石时间,处理1个矿石消耗能量等于purPerGenTime * purMaxExtract")
        @Config.LangKey("oceanheartr.config.general.phi.perGen")
        @Config.Name("purPerGenTime")
        public int phiPerGenTime = 100;
    }
}
