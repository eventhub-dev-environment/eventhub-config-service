## Tenant configuration access

Reading or saving `/api/v1/configs/{tenantId}` requires `TENANT_ADMIN` for that tenant or `PLATFORM_ADMIN`. Tenant administrators must send `X-Tenant-ID` for the active tenant; it must match the path or configuration body's `tenantId`. The API Gateway validates that membership before forwarding the request.

`EVENT_ORGANIZER` does not receive configuration access because this document includes tenant-wide settings such as transaction commissions. The public registration endpoint does not grant tenant memberships.