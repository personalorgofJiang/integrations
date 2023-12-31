package app.revanced.bilibili.patches;

import com.bapis.bilibili.app.playerunite.v1.PlayViewUniteReq;
import com.bapis.bilibili.playershared.VideoVod;
import com.bapis.bilibili.playershared.VideoVodEx;

import app.revanced.bilibili.settings.Settings;
import app.revanced.bilibili.utils.Constants;

public class VideoQualityPatch {

    public static int halfScreenQuality() {
        String qualityStr = Settings.HALF_SCREEN_QUALITY.getString();
        return Integer.parseInt(qualityStr);
    }

    public static int fullScreenQuality() {
        String qualityStr = Settings.FULL_SCREEN_QUALITY.getString();
        return Integer.parseInt(qualityStr);
    }

    /**
     * fullscreen video quality
     * <p>
     * codes will filled by patcher
     */
    public static int defaultQn() {
        return 0;
    }

    /**
     * unlock 8k limit
     * <p>
     * for old player PGC
     */
    public static void unlockLimit(com.bapis.bilibili.pgc.gateway.player.v1.PlayViewReq playViewReq) {
        int halfScreenQuality = halfScreenQuality();
        int fulledScreenQuality = fullScreenQuality();
        if (halfScreenQuality != 0 || fulledScreenQuality != 0) {
            com.bapis.bilibili.pgc.gateway.player.v1.PlayViewReqEx.setFnval(playViewReq, Constants.MAX_FNVAL);
            com.bapis.bilibili.pgc.gateway.player.v1.PlayViewReqEx.setFourk(playViewReq, true);
        }
    }

    /**
     * unlock 8k limit
     * <p>
     * for old player PGC
     */
    public static void unlockLimit(com.bapis.bilibili.pgc.gateway.player.v2.PlayViewReq playViewReq) {
        int halfScreenQuality = halfScreenQuality();
        int fulledScreenQuality = fullScreenQuality();
        if (halfScreenQuality != 0 || fulledScreenQuality != 0) {
            com.bapis.bilibili.pgc.gateway.player.v2.PlayViewReqEx.setFnval(playViewReq, Constants.MAX_FNVAL);
            com.bapis.bilibili.pgc.gateway.player.v2.PlayViewReqEx.setFourk(playViewReq, true);
        }
    }

    /**
     * unlock 8k limit
     * <p>
     * for old player UGC
     */
    public static void unlockLimit(com.bapis.bilibili.app.playurl.v1.PlayViewReq playViewReq) {
        int halfScreenQuality = halfScreenQuality();
        int fulledScreenQuality = fullScreenQuality();
        if (halfScreenQuality != 0 || fulledScreenQuality != 0) {
            com.bapis.bilibili.app.playurl.v1.PlayViewReqEx.setFnval(playViewReq, Constants.MAX_FNVAL);
            com.bapis.bilibili.app.playurl.v1.PlayViewReqEx.setFourk(playViewReq, true);
        }
    }

    /**
     * unlock 8k limit
     * <p>
     * for new unite(PGC + UGC) player
     */
    public static void unlockLimit(PlayViewUniteReq playViewReq) {
        VideoVod videoVod = playViewReq.getVod();
        int halfScreenQuality = halfScreenQuality();
        int fulledScreenQuality = fullScreenQuality();
        if (halfScreenQuality != 0 || fulledScreenQuality != 0) {
            VideoVodEx.setFnval(videoVod, Constants.MAX_FNVAL);
            VideoVodEx.setFourk(videoVod, true);
        }
    }

    /**
     * another better way to hook default half screen quality.
     */
    public static int getMatchedHalfScreenQuality() {
        int halfScreenQuality = halfScreenQuality();
        if (halfScreenQuality != 1) // not follow fullscreen quality
            return halfScreenQuality;
        return defaultQn();
    }
}
