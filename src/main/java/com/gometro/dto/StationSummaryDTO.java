package com.gometro.dto;

public class StationSummaryDTO {
    private Long id;
    private String stationCode;
    private String stationName;

    public StationSummaryDTO() {}

    public StationSummaryDTO(Long id, String stationCode, String stationName) {
        this.id = id;
        this.stationCode = stationCode;
        this.stationName = stationName;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getStationCode() { return stationCode; }
    public void setStationCode(String stationCode) { this.stationCode = stationCode; }
    public String getStationName() { return stationName; }
    public void setStationName(String stationName) { this.stationName = stationName; }
}
