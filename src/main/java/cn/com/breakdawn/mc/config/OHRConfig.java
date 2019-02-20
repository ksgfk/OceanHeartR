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
        @Config.Comment("Set Nature Dynamo max energy storage")
        @Config.LangKey("oceanheartr.config.general.dynNature.maxEnergy")
        @Config.Name("dynNatureMaxEnergy")
        public int dynNatureMaxEnergy = 32000000;

        @Config.Comment("Set Nature Dynamo max energy extract")
        @Config.LangKey("oceanheartr.config.general.dynNature.maxExtract")
        @Config.Name("dynNatureMaxExtract")
        public int dynNatureMaxExtract = 1000;

        @Config.Comment("Set Nature Dynamo max energy Receive")
        @Config.LangKey("oceanheartr.config.general.dynNature.maxReceive")
        @Config.Name("dynNatureMaxReceive")
        public int dynNatureMaxReceive = 1000;

        @Config.Comment("Set Nature Dynamo that each crystal power generation")
        @Config.LangKey("oceanheartr.config.general.dynNature.perGen")
        @Config.Name("dynNaturePerGen")
        public int dynNaturePerGen = 1000000;

        @Config.Comment("Set can put Nature ore in nether into dyn")
        @Config.LangKey("oceanheartr.config.general.dynNature.canPut")
        @Config.Name("dynNatureCanPut")
        public boolean dynNatureCanPut = false;

        @Config.Comment("Set Whether Sapling of Yggdrasill can grow")
        @Config.LangKey("oceanheartr.config.general.yggdrasill.grow")
        @Config.Name("canYggdrasillGrow")
        public boolean canYggdrasillGrow = true;

        @Config.Comment("Set Pulverizer max energy storage")
        @Config.LangKey("oceanheartr.config.general.pul.maxEnergy")
        @Config.Name("pulMaxEnergy")
        public int pulMaxEnergy = 320000;

        @Config.Comment("Set Pulverizer max energy receive")
        @Config.LangKey("oceanheartr.config.general.pul.maxReceive")
        @Config.Name("pulMaxReceive")
        public int pulMaxReceive = 80;

        @Config.Comment("Set Pulverizer max energy extract and process one ore's energy equals pulPerGenTime * pulMaxExtract")
        @Config.LangKey("oceanheartr.config.general.pul.maxExtract")
        @Config.Name("pulMaxExtract")
        public int pulMaxExtract = 10;

        @Config.Comment("Set Pulverizer process one ore's time and process one ore's energy equals pulPerGenTime * pulMaxExtract")
        @Config.LangKey("oceanheartr.config.general.pul.perGen")
        @Config.Name("pulPerGenTime")
        public int pulPerGenTime = 100;

        @Config.Comment("Set whether nature sword can hit animal")
        @Config.LangKey("oceanheartr.config.general.natureSworld.canHitAnimal")
        @Config.Name("natureSwordCanHitAnimal")
        public boolean natureSwordCanHitAnimal = false;

        @Config.Comment("Set nature sword return blood in PVP")
        @Config.LangKey("oceanheartr.config.general.natureSworld.pvp")
        @Config.Name("natureSwordPVP")
        public int natureSwordPVP = 5;

        @Config.Comment("Set nature sword return blood on hiting mob ")
        @Config.LangKey("oceanheartr.config.general.natureSworld.mob")
        @Config.Name("natureSwordMob")
        public float natureSwordMob = 0.25f;

        @Config.Comment("Set cd of VladIII's skill")
        @Config.LangKey("oceanheartr.config.general.vladiii.cd")
        @Config.Name("vladIIIMaxCD")
        public int vladIIIMaxCD = 300;
    }
}
