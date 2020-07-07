package com.arcsoft.facetest.model;



import java.util.List;

/**
 * <p>
 * score坐标集合列表
 * </p>
 *
 * @author goinhn
 * @version 0.0.1
 * @date 2020-07-02
 * @since 0.0.1
 */
public class CoordinatesList {

    /**
     * 坐标集合列表
     */
    private List<Coordinates> coordinates;

    /**
     * 是否存在该数据
     */
    private boolean isExists;

    public CoordinatesList() {
    }

    public CoordinatesList(List<Coordinates> coordinates, boolean isExists) {
        this.coordinates = coordinates;
        this.isExists = isExists;
    }

    public List<Coordinates> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Coordinates> coordinates) {
        this.coordinates = coordinates;
    }

    public boolean isExists() {
        return isExists;
    }

    public void setExists(boolean exists) {
        isExists = exists;
    }

    @Override
    public String toString() {
        return "ScoreCoordinatesList{" +
                "coordinates=" + coordinates +
                ", isExists=" + isExists +
                '}';
    }
}
