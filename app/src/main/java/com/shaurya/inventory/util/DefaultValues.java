package com.shaurya.inventory.util;

/**
 * Created by ishan.jaiswal on 5/23/2018.
 */

public class DefaultValues {

    private static final String STOCK_IN = "STOCK_IN";
    private static final String STOCK_OUT = "STOCK_OUT";
    private static final String PACKAGING_MATERIAL = "PACKAGING_MATERIAL";
    public static String stringEncodedLogoImage(){
        return "/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAYEBQYFBAYGBQYHBwYIChAKCgkJChQODwwQFxQYGBcUFhYaHSUfGhsjHBYWICwgIyYnKSopGR8tMC0oMCUoKSgBBwcHCggKEwoKEygaFhooKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKP/AABEIAPoB7wMBEQACEQEDEQH/xAGiAAABBQEBAQEBAQAAAAAAAAAAAQIDBAUGBwgJCgsQAAIBAwMCBAMFBQQEAAABfQECAwAEEQUSITFBBhNRYQcicRQygZGhCCNCscEVUtHwJDNicoIJChYXGBkaJSYnKCkqNDU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6g4SFhoeIiYqSk5SVlpeYmZqio6Slpqeoqaqys7S1tre4ubrCw8TFxsfIycrS09TV1tfY2drh4uPk5ebn6Onq8fLz9PX29/j5+gEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoLEQACAQIEBAMEBwUEBAABAncAAQIDEQQFITEGEkFRB2FxEyIygQgUQpGhscEJIzNS8BVictEKFiQ04SXxFxgZGiYnKCkqNTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqCg4SFhoeIiYqSk5SVlpeYmZqio6Slpqeoqaqys7S1tre4ubrCw8TFxsfIycrS09TV1tfY2dri4+Tl5ufo6ery8/T19vf4+fr/2gAMAwEAAhEDEQA/APqHT/8Ajyh/3RQBYoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgDjfjL/ySnxb/ANgq6/8ARLUAdVp//HlD/uigCxQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFAHG/GX/klPi3/sFXX/olqAOq0/8A48of90UAWKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoA434y/8AJKfFv/YKuv8A0S1AHVaf/wAeUP8AuigCxQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFAHG/GX/klPi3/ALBV1/6JagDqtP8A+PKH/dFAFigAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKAON+Mv8AySnxb/2Crr/0S1AHVaf/AMeUP+6KALFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAcb8Zf+SU+Lf+wVdf8AolqAOq0//jyh/wB0UAWKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAM0AFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQBxvxl/wCSU+Lf+wVdf+iWoA6rT/8Ajyh/3RQBYoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKAIbq5htkDXEqRqTjLHFAEisGAZSCp6Ed6AHUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFAHG/GX/klPi3/sFXX/olqAOq0/8A48of90UAWKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKAENAHkH7RVokuiadM935W2cKIz0bIPP4VpAD0XwZbi18LaZEs5uFECESHuCoqJ7gbdI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AcOlACGgAoASgB1ABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAcb8Zf+SU+Lf+wVdf8AolqAOq0//jyh/wB0UAWKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoA434y/8kp8W/8AYKuv/RLUAdVp/wDx5Q/7ooAsUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQBxvxl/wCSU+Lf+wVdf+iWoA6rT/8Ajyh/3RQBYoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgDjfjL/ySnxb/ANgq6/8ARLUAdVp//HlD/uigCxQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFAHG/GX/klPi3/sFXX/olqAOq0/8A48of90UAWKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoA434y/8AJKfFv/YKuv8A0S1AHVaf/wAeUP8AuigCxQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFAHG/GX/klPi3/ALBV1/6JagDprGaNbOIM6gheQTigCx58X/PRP++hQAvnRf8APVP++hQAedF/z1T/AL6FAB50X/PVP++hQAedF/z1T/voUAHnRf8APVP++hQAedF/z1T/AL6FAB50X/PVP++hQAedF/z1T/voUAHnRf8APVP++hQAedF/z1T/AL6FAB50X/PVP++hQAedF/z1T/voUAHnRf8APVP++hQAedF/z1T/AL6FAB50X/PVP++hQAedF/z1T/voUAHnRf8APVP++hQAedF/z1T/AL6FAB50X/PVP++hQAedF/z1T/voUAHnRf8APVP++hQAedF/z1T/AL6FAB50X/PVP++hQAedF/z1T/voUAHnRf8APVP++hQAedF/z1T/AL6FAB50X/PVP++hQAedF/z1T/voUAHnRf8APVP++hQAedF/z1T/AL6FAB50X/PVP++hQAedF/z1T/voUAHnRf8APVP++hQAedF/z1T/AL6FAB50X/PVP++hQAedF/z1T/voUAHnRf8APVP++hQAedF/z1T/AL6FAB50X/PVP++hQAedF/z1T/voUAHnRf8APVP++hQAedF/z1T/AL6FAB50X/PVP++hQAedF/z1T/voUAHnRf8APVP++hQAedF/z1T/AL6FAB50X/PVP++hQAedF/z1T/voUAHnRf8APVP++hQAedF/z1T/AL6FAB50X/PVP++hQAedF/z1T/voUAHnRf8APVP++hQAedF/z1T/AL6FAB50X/PVP++hQAedF/z1T/voUAHnRf8APVP++hQAedF/z1T/AL6FAB50X/PVP++hQBxvxikR/hX4uCMrH+yrroc/8sWoA+Mf+F+fEokk+IkJP/UOtf8A41QAf8L8+JP/AEMSf+C61/8AjVAB/wAL8+JX/QxJ/wCC61/+NUAH/C/PiV/0MSf+C61/+NUAH/C/PiV/0MSf+C61/wDjVAB/wvz4lf8AQxJ/4LrX/wCNUAH/AAvz4lf9DEn/AILrX/41QAf8L8+JX/QxJ/4LrX/41QAf8L8+JX/QxJ/4LrX/AONUAH/C/PiV/wBDEn/gutf/AI1QAf8AC/PiV/0MSf8Agutf/jVAB/wvz4lf9DEn/gutf/jVAB/wvz4lf9DEn/gutf8A41QAf8L8+JX/AEMSf+C61/8AjVAB/wAL8+JX/QxJ/wCC61/+NUAH/C/PiV/0MSf+C61/+NUAH/C/PiV/0MSf+C61/wDjVAB/wvz4lf8AQxJ/4LrX/wCNUAH/AAvz4lf9DEn/AILrX/41QAf8L8+JX/QxJ/4LrX/41QAf8L8+JX/QxJ/4LrX/AONUAH/C/PiV/wBDEn/gutf/AI1QAf8AC/PiV/0MSf8Agutf/jVAB/wvz4lf9DEn/gutf/jVAB/wvz4lf9DEn/gutf8A41QAf8L8+JX/AEMSf+C61/8AjVAB/wAL8+JX/QxJ/wCC61/+NUAH/C/PiV/0MSf+C61/+NUAH/C/PiV/0MSf+C61/wDjVAB/wvz4lf8AQxJ/4LrX/wCNUAH/AAvz4lf9DEn/AILrX/41QAf8L8+JX/QxJ/4LrX/41QAf8L8+JX/QxJ/4LrX/AONUAH/C/PiV/wBDEn/gutf/AI1QAf8AC/PiV/0MSf8Agutf/jVAB/wvz4lf9DEn/gutf/jVAB/wvz4lf9DEn/gutf8A41QAf8L8+JX/AEMSf+C61/8AjVAB/wAL8+JX/QxJ/wCC61/+NUAH/C/PiV/0MSf+C61/+NUAH/C/PiV/0MSf+C61/wDjVAB/wvz4lf8AQxJ/4LrX/wCNUAH/AAvz4lf9DEn/AILrX/41QAf8L8+JX/QxJ/4LrX/41QAf8L8+JX/QxJ/4LrX/AONUAH/C/PiV/wBDEn/gutf/AI1QAf8AC/PiV/0MSf8Agutf/jVAB/wvz4lf9DEn/gutf/jVAB/wvz4lf9DEn/gutf8A41QAf8L8+JX/AEMSf+C61/8AjVAB/wAL8+JX/QxJ/wCC61/+NUAH/C/PiV/0MSf+C61/+NUAH/C/PiV/0MSf+C61/wDjVAB/wvz4lf8AQxJ/4LrX/wCNUAH/AAvz4lf9DEn/AILrX/41QAf8L8+JX/QxJ/4LrX/41QAf8L8+JX/QxJ/4LrX/AONUAH/C/PiV/wBDEn/gutf/AI1QAf8AC/PiV/0MSf8Agutf/jVAB/wvz4lf9DEn/gutf/jVAB/wvz4lf9DEn/gutf8A41QBW1X42/EHVdLvNOv9eSWzvIXt54xYWy743UqwyIwRkE8g5oA//9k=";
    }

    public static String getStockMode(int n){
        String stockMode="";
        switch (n){
            case 1:
                stockMode = STOCK_IN;
                break;
            case 2:
                stockMode = STOCK_OUT;
                break;
            case 3:
                stockMode = PACKAGING_MATERIAL;
                break;
        }
        return stockMode;
    }

}
