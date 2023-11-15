package christmas.consts;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

class ChristmasMenuTest extends ChristmasMenuFixture{

    @Test
    void 메뉴명으로_크리스마스_메뉴_조회() {
        ChristmasMenu result = ChristmasMenu.findByMenu(유효한_메뉴);
        assertThat(result).isEqualTo(ChristmasMenu.MAIN);
    }

    @Test
    void 메뉴명으로_크리스마스_메뉴_가격_조회() {
        ChristmasMenu christmasMenu = ChristmasMenu.findByMenu(유효한_메뉴);
        HashMap<String, Integer> getChristmasMenu = christmasMenu.getMenu();
        int result = getChristmasMenu.get(유효한_메뉴);
        assertThat(result).isEqualTo(유효한_메뉴_가격);
    }
}