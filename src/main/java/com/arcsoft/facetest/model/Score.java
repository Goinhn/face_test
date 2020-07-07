package com.arcsoft.facetest.model;

import java.util.List;

/**
 * <p>
 * score常量模型
 * </p>
 *
 * @author goinhn
 * @version 0.0.1
 * @date 2020-07-02
 * @since 0.0.1
 */
public class Score {

    /**
     * score列表
     */
    private List<Double> scoreList;

    /**
     * 是否存在该数据
     */
    private boolean isExists;

    public Score() {}

    public Score(List<Double> scoreList, boolean isExists) {
        this.scoreList = scoreList;
        this.isExists = isExists;
    }

    public List<Double> getScoreList() {
        return scoreList;
    }

    public void setScoreList(List<Double> scoreList) {
        this.scoreList = scoreList;
    }

    public boolean isExists() {
        return isExists;
    }

    public void setExists(boolean exists) {
        isExists = exists;
    }

    @Override
    public String toString() {
        return "Score{" +
                "scoreList=" + scoreList +
                ", isExists=" + isExists +
                '}';
    }
}
