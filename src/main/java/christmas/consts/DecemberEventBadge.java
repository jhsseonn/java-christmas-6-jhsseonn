package christmas.consts;

import christmas.consts.ChristmasConsts;

public enum DecemberEventBadge implements ChristmasConsts {
    STAR(DECEMBER_EVENT_BADGE_STAR_AMOUNT, BADGE_NAME_STAR),
    TREE(DECEMBER_EVENT_BADGE_TREE_AMOUNT, BADGE_NAME_TREE),
    SANTA(DECEMBER_EVENT_BADGE_SANTA_AMOUNT, BADGE_NAME_SANTA),
    NONE(DECEMBER_EVENT_BADGE_NONE_AMOUNT, BADGE_NAME_NONE);

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
