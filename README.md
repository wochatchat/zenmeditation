# ZenMeditation
        
> **Automated Android Architecture & Continuous Integration Specification Document**
> Generated dynamically by Sandboxed AI Coding Agent.

## 1. Project Overview (项目概述)
- **Application Name (应用名称):** ZenMeditation
- **Package Dimension (包名划分):** `com.aistudio.zenmeditation.app`
- **Functional Context (功能简述):** 交互式深呼吸冥想与自然白噪音助眠应用

---

## 2. Architecture & Tech Stack (技术架构与规范)
This production code adheres strictly to modern Android development architecture paradigms, emphasizing separation of concerns, offline-first reliability, and reactive data flow patterns.

- **UI Composition:** 100% Jetpack Compose using Material Design 3 (M3).
- **Architecture Pattern:** MVVM (Model-View-ViewModel) + Clean Architecture principles.
- **State Flow Engine:** Kotlin Coroutines & `MutableStateFlow` streams for deterministic UI transitions.
- **Data Persistence:** Offline storage persistence designed via Jetpack Room DB or local preferences if applicable.
- **Asynchronous Execution:** Structured concurrency using lifecycle-aware coroutines scopes.

---

## 3. Directory Layout (目录构造细节)
```text
.
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/ (Application namespaces and domain packages)
│   │   │   ├── res/
│   │   │   │   ├── values/ (Themes, colors, and layout strings)
│   │   │   │   └── drawable/ (Custom Vector and Adaptive launcher resources)
│   │   │   └── AndroidManifest.xml (Global capabilities declaration and permissions)
│   │   └── build.gradle.kts (App-level module dependencies & SDK variants)
├── .github/workflows/
│   └── build.yml (CI/CD Automated continuous compilation pipeline)
├── gradle.properties (JVM configuration parameters)
└── settings.gradle.kts (Gradle project settings catalog)
```

---

## 4. Build and Compile Pipeline (云端编译与构建脚本规范)
Our repository integrates a seamless GitHub Actions workflow to support automated cloud compilation for both **Debug** and **Release** environments.

### Build Commands (打包命令):
1. **Debug Stream (调试测试包):**
   ```bash
   ./gradlew assembleDebug
   ```
2. **Release Stream (官方签名发布包):**
   ```bash
   ./gradlew assembleRelease
   ```

### Signing Configuration (签名配置):
When compilation is triggered in **Release Build Mode**, the actions pipeline dynamically captures secure environment credentials provided from the sandbox to sign the output alignment:
- `KEYSTORE_BASE64`: Encrypted base64 Keystore bytes.
- `KEY_ALIAS`: Target certificate key identification alias.
- `KEYSTORE_PWD`: Secure database/store access password.
- `KEY_PWD`: Matching alias cryptographic private key password.

---

## 5. Deployment Information (编译与部署)
- Built APK artifacts are packaged securely in GitHub Actions and available to download instantly in the Run Artifacts collection.
- Local mobile side utilizes automated Retrofit log streaming loops to trace compilation feedback in real-time.