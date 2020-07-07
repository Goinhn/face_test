package com.arcsoft.facetest.model;

/**
 * <p>
 * 上下左右的位置信息
 * </p>
 *
 * @author goinhn
 * @version 0.0.1
 * @date 2020-07-02
 * @since 0.0.1
 */
public class Location {

    /**
     * 左坐标
     */
    private Double left;

    /**
     * 上坐标
     */
    private Double top;

    /**
     * 右坐标
     */
    private Double right;

    /**
     * 下坐标
     */
    private Double bottom;

    /**
     * 是否存在该数据
     */
    private boolean isExists;

    public Location() {}

    public Location(Double left, Double top, Double right, Double bottom, boolean isExists) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.isExists = isExists;
    }

    public Double getLeft() {
        return left;
    }

    public void setLeft(Double left) {
        this.left = left;
    }

    public Double getTop() {
        return top;
    }

    public void setTop(Double top) {
        this.top = top;
    }

    public Double getRight() {
        return right;
    }

    public void setRight(Double right) {
        this.right = right;
    }

    public Double getBottom() {
        return bottom;
    }

    public void setBottom(Double bottom) {
        this.bottom = bottom;
    }

    public boolean isExists() {
        return isExists;
    }

    public void setExists(boolean exists) {
        isExists = exists;
    }

    @Override
    public String toString() {
        return "Location{" +
                "left=" + left +
                ", top=" + top +
                ", right=" + right +
                ", bottom=" + bottom +
                ", isExists=" + isExists +
                '}';
    }
}
