package com.eventhub.config_service.infrastructure.adapter.in.web;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TenantConfigAccessPolicyTest {

    private final TenantConfigAccessPolicy policy = new TenantConfigAccessPolicy();

    @Test
    void deniesStaffFromAnotherTenant() {
        assertThatThrownBy(() -> policy.requireTenantAdmin(
                "tenant-a", "tenant-b", "TENANT_ADMIN", "CUSTOMER"))
                .isInstanceOf(ResponseStatusException.class)
                .satisfies(exception -> assertThat(((ResponseStatusException) exception).getStatusCode())
                        .isEqualTo(HttpStatus.FORBIDDEN));
    }

    @Test
    void permitsMatchingTenantAndPlatformAdmin() {
        assertThatCode(() -> policy.requireTenantAdmin(
                "tenant-a", "tenant-a", "TENANT_ADMIN", "CUSTOMER"))
                .doesNotThrowAnyException();
        assertThatCode(() -> policy.requireTenantAdmin(
                "tenant-a", null, "", "PLATFORM_ADMIN"))
                .doesNotThrowAnyException();
        assertThatThrownBy(() -> policy.requireTenantAdmin(
                "tenant-a", "tenant-a", "EVENT_ORGANIZER", "CUSTOMER"))
                .isInstanceOf(ResponseStatusException.class);
    }
}
