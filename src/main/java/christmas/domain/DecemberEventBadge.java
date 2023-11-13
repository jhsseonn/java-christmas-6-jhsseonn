package christmas.domain;

import christmas.consts.ChristmasConsts;

public enum DecemberEventBadge implements ChristmasConsts {
    STAR(DECEMBER_EVENT_BADGE_STAR_AMOUNT),
    TREE(DECEMBER_EVENT_BADGE_TREE_AMOUNT),
    SANTA(DECEMBER_EVENT_BADGE_SANTA_AMOUNT),
    NONE(DECEMBER_EVENT_BADGE_NONE_AMOUNT);

    private int totalAdvantageAmount;

    DecemberEventBadge(int totalAdvantageAmount){
        this.totalAdvantageAmount = totalAdvantageAmount;
    }
}
