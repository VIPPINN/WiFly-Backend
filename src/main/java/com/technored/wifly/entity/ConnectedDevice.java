package com.technored.wifly.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "connected_devices")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ConnectedDevice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    @Size(max = 17)
    @Column(name = "mac_address", unique = true)
    private String macAddress;

    @Size(max = 15)
    @Column(name = "ip_address")
    private String ipAddress;

    @Size(max = 100)
    @Column(name = "device_type")
    private String deviceType; // Phone, Laptop, Smart TV, etc.

    @Size(max = 100)
    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "is_connected")
    @Builder.Default
    private Boolean isConnected = true;

    @Column(name = "signal_strength")
    private Integer signalStrength; // -100 to 0 dBm

    @Column(name = "data_usage_mb")
    @Builder.Default
    private Long dataUsageMB = 0L;

    @Column(name = "last_seen")
    private LocalDateTime lastSeen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wifi_network_id", nullable = false)
    private WiFiNetwork wifiNetwork;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
