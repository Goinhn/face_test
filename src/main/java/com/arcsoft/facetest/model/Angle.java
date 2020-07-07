package com.arcsoft.facetest.model;

/**
 * <p>
 * 角度常量
 * </p>
 *
 * @author goinhn
 * @version 0.0.1
 * @date 2020-07-02
 * @since 0.0.1
 */
public class Angle {

    /**
     *
     */
    private Double roll;

    /**
     *
     */
    private Double yaw;

    /**
     *
     */
    private Double pitch;

    /**
     * 是否存在
     */
    private boolean isExists;

    public Angle() {
    }

    public Angle(Double roll, Double yaw, Double pitch, boolean isExists) {
        this.roll = roll;
        this.yaw = yaw;
        this.pitch = pitch;
        this.isExists = isExists;
    }

    public Double getRoll() {
        return roll;
    }

    public void setRoll(Double roll) {
        this.roll = roll;
    }

    public Double getYaw() {
        return yaw;
    }

    public void setYaw(Double yaw) {
        this.yaw = yaw;
    }

    public Double getPitch() {
        return pitch;
    }

    public void setPitch(Double pitch) {
        this.pitch = pitch;
    }

    public boolean isExists() {
        return isExists;
    }

    public void setExists(boolean exists) {
        isExists = exists;
    }

    @Override
    public String toString() {
        return "Angle{" +
                "roll=" + roll +
                ", yaw=" + yaw +
                ", pitch=" + pitch +
                ", isExists=" + isExists +
                '}';
    }
}
