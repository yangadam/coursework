package cn.edu.xmu.comm.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

/**
 * 停车场实体
 * Created by Roger on 2014/12/23 0023.
 */
@Entity
@DynamicInsert
@DynamicUpdate
public class ParkingLot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @OneToMany(mappedBy = "parkingLot", targetEntity = ParkingPlace.class, cascade = CascadeType.ALL)
    private List<ParkingPlace> parkingPlaces = new ArrayList<ParkingPlace>();
    @ManyToOne(targetEntity = Community.class, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "community_id", nullable = false)
    private Community community;
    private ParkingLotStatus type;
    private String feeType;
    @OrderBy
    @Column(name = "gradient_value")
    @ElementCollection
    @CollectionTable(
            name = "parking_gradient",
            joinColumns = @JoinColumn(name = "parklot_id")
    )
    private SortedMap<Integer, BigDecimal> gradient = new TreeMap<Integer, BigDecimal>();

    /**
     * 获取停车场的可用车位的大小
     *
     * @return 可用大小
     */
    public Integer getSize() {
        return parkingPlaces.size();
    }

    /**
     * 计算临时停车费
     *
     * @param parkingTime 停车时长
     * @return 停车费用
     */
    public BigDecimal calculateTempParkingFee(Integer parkingTime) {
        BigDecimal parkingFee = BigDecimal.ZERO;
        for (Object o : gradient.entrySet()) {
            Map.Entry entry = (Map.Entry) o;
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

    /**
     * 获得停车场主键
     *
     * @return 停车场主键
     */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获得停车场名称
     *
     * @return 停车场名称
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获得包含的停车位
     *
     * @return 包含的停车位
     */
    public List<ParkingPlace> getParkingPlaces() {
        return parkingPlaces;
    }

    public void setParkingPlaces(List<ParkingPlace> parkingPlaces) {
        this.parkingPlaces = parkingPlaces;
    }

    /**
     * 获得所属小区
     *
     * @return 所属小区
     */
    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    /**
     * 获得停车场类型
     *
     * @return 停车场类型
     */
    public ParkingLotStatus getType() {
        return type;
    }

    public void setType(ParkingLotStatus type) {
        this.type = type;
    }

    /**
     * 获得停车场收费标准
     *
     * @return 停车场收费标准
     */
    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    /**
     * 获得梯度定义
     *
     * @return 梯度定义
     */
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
