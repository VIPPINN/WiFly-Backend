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
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "wifi_networks")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class WiFiNetwork {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    @Size(max = 50)
    @Column(name = "ssid")
    private String ssid;

    @Size(max = 100)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "security_type")
    private SecurityType securityType;

    @Column(name = "frequency_band")
    private String frequencyBand; // 2.4GHz, 5GHz, Both

    @Column(name = "channel")
    private Integer channel;

    @Column(name = "signal_strength")
    private Integer signalStrength; // -100 to 0 dBm

    @Column(name = "is_connected")
    private Boolean isConnected = false;

    @Column(name = "last_connected")
    private LocalDateTime lastConnected;

    @Column(name = "device_count")
    private Integer deviceCount = 0;

    @Column(name = "max_devices")
    private Integer maxDevices = 10;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "wifiNetwork", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private Set<ConnectedDevice> connectedDevices = new HashSet<>();

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public enum SecurityType {
        OPEN, WEP, WPA, WPA2, WPA3
    }
}
