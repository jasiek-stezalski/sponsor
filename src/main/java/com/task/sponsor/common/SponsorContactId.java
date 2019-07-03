package com.task.sponsor.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
public class SponsorContactId implements Serializable {

    @Column(name = "SPONSOR_ID", nullable = false)
    private long sponsorId;

    @Column(name = "CONTACT_ID", nullable = false)
    private long contactId;

    @Column(name = "BEGIN_DATE", nullable = false)
    private LocalDateTime beginDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SponsorContactId that = (SponsorContactId) o;
        return sponsorId == that.sponsorId &&
                contactId == that.contactId &&
                Objects.equals(beginDate, that.beginDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sponsorId, contactId, beginDate);
    }
}
