-- SQL schema for HeritageGraph360 (PostgreSQL)

CREATE TABLE tenants (
    tenant_id VARCHAR(64) PRIMARY KEY,
    region VARCHAR(16) NOT NULL,
    org_id VARCHAR(32) NOT NULL,
    name VARCHAR(128) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE accounts (
    account_id UUID PRIMARY KEY,
    tenant_id VARCHAR(64) REFERENCES tenants(tenant_id),
    email VARCHAR(255) UNIQUE,
    phone VARCHAR(32) UNIQUE,
    display_name VARCHAR(128) NOT NULL,
    status VARCHAR(32) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE roles (
    role_id UUID PRIMARY KEY,
    tenant_id VARCHAR(64) REFERENCES tenants(tenant_id),
    name VARCHAR(64) NOT NULL
);

CREATE TABLE permissions (
    permission_id UUID PRIMARY KEY,
    name VARCHAR(128) NOT NULL
);

CREATE TABLE policies (
    policy_id UUID PRIMARY KEY,
    tenant_id VARCHAR(64) REFERENCES tenants(tenant_id),
    name VARCHAR(128) NOT NULL,
    effect VARCHAR(16) NOT NULL,
    resource VARCHAR(128) NOT NULL,
    action VARCHAR(64) NOT NULL,
    conditions_json TEXT
);

CREATE TABLE role_permissions (
    role_id UUID REFERENCES roles(role_id),
    permission_id UUID REFERENCES permissions(permission_id),
    PRIMARY KEY (role_id, permission_id)
);

CREATE TABLE account_roles (
    account_id UUID REFERENCES accounts(account_id),
    role_id UUID REFERENCES roles(role_id),
    PRIMARY KEY (account_id, role_id)
);

CREATE TABLE profiles (
    profile_id UUID PRIMARY KEY,
    tenant_id VARCHAR(64) REFERENCES tenants(tenant_id),
    primary_email VARCHAR(255),
    primary_phone VARCHAR(32),
    display_name VARCHAR(128),
    claimed BOOLEAN NOT NULL DEFAULT FALSE,
    visibility VARCHAR(16) NOT NULL DEFAULT 'PRIVATE',
    created_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE relationships (
    relationship_id UUID PRIMARY KEY,
    tenant_id VARCHAR(64) REFERENCES tenants(tenant_id),
    profile_id UUID REFERENCES profiles(profile_id),
    related_profile_id UUID REFERENCES profiles(profile_id),
    status VARCHAR(16) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE audit_logs (
    audit_id UUID PRIMARY KEY,
    tenant_id VARCHAR(64) REFERENCES tenants(tenant_id),
    actor_id UUID REFERENCES accounts(account_id),
    action VARCHAR(128) NOT NULL,
    entity_id UUID NOT NULL,
    details TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE approvals (
    approval_id UUID PRIMARY KEY,
    tenant_id VARCHAR(64) REFERENCES tenants(tenant_id),
    profile_id UUID REFERENCES profiles(profile_id),
    requested_by UUID REFERENCES accounts(account_id),
    status VARCHAR(16) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE merges (
    merge_id UUID PRIMARY KEY,
    tenant_id VARCHAR(64) REFERENCES tenants(tenant_id),
    source_profile_id UUID REFERENCES profiles(profile_id),
    target_profile_id UUID REFERENCES profiles(profile_id),
    status VARCHAR(32) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW()
);
