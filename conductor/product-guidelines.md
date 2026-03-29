# Product Guidelines

## 1. Design Principles
- **Modularity:** Ensure each convention plugin operates independently, applying configurations strictly to its designated domain (e.g., Android, Rust) without interfering with others.
- **Convention over Configuration:** Provide sensible defaults that cover 80% of use cases out-of-the-box, but allow developers to override these through dedicated extension blocks when necessary.
- **Fail Fast:** Fail the build early and with clear, actionable error messages if required environments or configurations are missing (e.g., missing SDK paths, incorrect toolchain versions).

## 2. Developer Experience (DX)
- **Minimal Boilerplate:** Applying a plugin should reduce the host project's build script length significantly.
- **Idiomatic Kotlin DSL:** Use idiomatic, type-safe Kotlin DSL configurations internally to provide auto-completion and documentation for plugin users.
- **Transparency:** The plugins should not be "black boxes". Key operations (e.g., Rust compilation steps) should output reasonable logging without being overly verbose during normal builds.

## 3. Maintenance and Versioning
- **Semantic Versioning:** Clearly define compatible AGP, Kotlin, and Gradle versions for every release. Ensure breaking changes are well documented and tracked via major version bumps.
- **Centralized Dependencies:** Utilize a centralized version catalog (`libs.versions.toml` or similar) to manage the versions of standard dependencies supplied by these plugins.