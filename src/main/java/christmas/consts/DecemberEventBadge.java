package christmas.consts;

import christmas.consts.ChristmasConsts;

public enum DecemberEventBadge implements ChristmasConsts {
    STAR(DECEMBER_EVENT_BADGE_STAR_AMOUNT, "별"),
    TREE(DECEMBER_EVENT_BADGE_TREE_AMOUNT, "트리"),
    SANTA(DECEMBER_EVENT_BADGE_SANTA_AMOUNT, "산타"),
    NONE(DECEMBER_EVENT_BADGE_NONE_AMOUNT, "없음");

    private int totalAdvantageAmount;
    private String badgeName;

    DecemberEventBadge(int totalAdvantageAmount, String badgeName){
        this.totalAdvantageAmount = totalAdvantageAmount;
        this.badgeName = badgeName;
    }

    public String getDecemberEventBadge(){
        return badgeName;
    }
}
