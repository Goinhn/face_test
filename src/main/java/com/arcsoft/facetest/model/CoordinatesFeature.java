package com.arcsoft.facetest.model;


import java.util.List;
import java.util.Map;

/**
 * <p>
 * 每个点坐标对应的特征模型
 * </p>
 *
 * @author goinhn
 * @version 0.0.1
 * @date 2020-07-02
 * @since 0.0.1
 */
public class CoordinatesFeature {

    /**
     * 坐标集合
     */
    private List<Coordinates> coordinates;

    /**
     * 分类结果存储
     */
    private Map<Integer, String> classification;

    public CoordinatesFeature() {
    }

    public CoordinatesFeature(List<Coordinates> coordinates, Map<Integer, String> classification) {
        this.coordinates = coordinates;
        this.classification = classification;
    }

    public List<Coordinates> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Coordinates> coordinates) {
        this.coordinates = coordinates;
    }

    public Map<Integer, String> getClassification() {
        return classification;
    }

    public void setClassification(Map<Integer, String> classification) {
        this.classification = classification;
    }

    @Override
    public String toString() {
        return "CoorClass{" +
                "coordinates=" + coordinates +
                ", classification=" + classification +
                '}';
    }
}
