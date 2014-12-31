package cn.edu.xmu.comm.entity;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Roger on 2014/12/23 0023.
 */
@Entity
@DynamicInsert
public class ParkingLot {
    //region Instance Variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 停车场名称
     */
    private String name;
    /**
     * 包含的停车位
     */
    @OneToMany(mappedBy = "parkingLot", targetEntity = ParkPlace.class, cascade = CascadeType.ALL)
    private List<ParkPlace> parkPlaces;
    /**
     * 所属小区
     */
    @ManyToOne(targetEntity = Community.class, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "community_id", nullable = false)
    private Community community;

    /**
     * 停车场类型，TEMP：临时车位停车场、RENT：租用车位停车场
     */
    private ParkingLotStatus type;
    //endregion

    //region Public Methods
    /**
     * 停车场收费标准
     */
    private String feeType;
    /**
     * 梯度定义
     */
    @ElementCollection
    @CollectionTable(
            name = "parking_gradient",
            joinColumns = @JoinColumn(name = "parklot_id")
    )
    @OrderBy
    @Column(name = "gradient_value")
    private SortedMap<Integer, BigDecimal> gradient = new TreeMap<Integer, BigDecimal>();
    //endregion

    //region Getters and Setters

    /**
     * 获取停车场的可用车位的大小
     *
     * @return 可用大小
     */
    public Integer getParkingLotSize() {
        return parkPlaces.size();
    }

    /**
     * 计算临时停车费
     *
     * @param parkingTime 停车时长
     * @return 停车费用
     */
    public BigDecimal calculateTempParkingFee(Integer parkingTime) {
        //Iterator it = gradient.entrySet().iterator();
        BigDecimal parkingFee = BigDecimal.ZERO;

        Iterator it = gradient.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            if (parkingTime == entry.getKey()) {
                parkingFee = (BigDecimal) entry.getValue();
                break;
            } else if (parkingTime < (Integer) entry.getKey()) {
                break;
            } else {
                parkingFee = (BigDecimal) entry.getValue();
            }
        }
        return parkingFee;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ParkPlace> getParkPlaces() {
        return parkPlaces;
    }

    public void setParkPlaces(List<ParkPlace> parkPlaces) {
        this.parkPlaces = parkPlaces;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public ParkingLotStatus getType() {
        return type;
    }

    public void setType(ParkingLotStatus type) {
        this.type = type;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    //endregion

    public SortedMap<Integer, BigDecimal> getGradient() {
        return gradient;
    }

    public void setGradient(SortedMap<Integer, BigDecimal> gradient) {
        this.gradient = gradient;
    }

    /**
     * 停车场类型，TEMP：临时车位停车场、RENT：租用车位停车场
     */
    public enum ParkingLotStatus {
        TEMP("临时停车场"), RENT("租用停车场");

        private String typeName;

        private ParkingLotStatus(String typeName) {
            this.typeName = typeName;
        }

        @Override
        public String toString() {
            return typeName;
        }
    }
}
