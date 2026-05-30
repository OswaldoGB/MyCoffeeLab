# MyCoffeeLab

> App Android nativa en Kotlin para que un barista emprendedor controle su inventario de café, recetas estandarizadas y bitácora de servicio.
>
> Proyecto de la asignatura **Técnicas de Producción Industrial de Software** — UTEC.
>
> **Autor:** Oswaldo Enrique Guevara Berríos · Carné 29-4645-2022
> **Catedrático:** Ing. Edgar Peñate

---

## 1. ¿Qué problema resuelve?

Coffee Alchemists es un emprendimiento de café de especialidad operado por Juan Pérez. Hoy se apoya en libretas y notas sueltas, lo que produce:

- Beans olvidados que pierden frescura.
- Recetas inconsistentes entre turnos.
- Sin trazabilidad de qué shots salieron bien y cuáles mal.
- Sin visibilidad del costo real por bebida.

MyCoffeeLab unifica todo eso en una sola app, **funcionando 100 % offline** (Room) y diseñada para registrar un shot en menos de 30 segundos durante el servicio.

---

## 2. Estado del proyecto

| Entrega | Unidad | Estado |
|---|---|---|
| Entrega 1 — Idea, problema, alcance | 1 | ✅ Entregada |
| Entrega 2 — Requisitos y prototipo | 2 | ✅ Entregada |
| **Entrega 3 — Arquitectura y base técnica** | **3** | **✅ Este repositorio** |
| Entrega 4 — Lógica de negocio y persistencia | 4 | ⏳ Próxima |
| Entrega final — App funcional + pruebas | 5 | ⏳ |

Lo que ya funciona en esta entrega:

- ✔️ Proyecto Android compila con Gradle 8.7 / AGP 8.5.2 / Kotlin 2.0.20.
- ✔️ Bottom Navigation con 4 destinos (Dashboard, Beans, Recetas, Bitácora).
- ✔️ Navegación tipada con argumentos a 4 sub-pantallas (detalle y formulario de beans y recetas).
- ✔️ Tema Material 3 con paleta café de marca.
- ✔️ Esqueleto Room (3 entidades, 3 DAOs, AppDatabase) listo para enchufar a los ViewModels en la próxima unidad.
- ✔️ Repositorios delgados como única puerta de entrada hacia los DAOs.

Lo que **todavía no** se hace en esta entrega (es deliberado, corresponde a la Entrega 4):

- ❌ ViewModels reales — las pantallas son placeholders.
- ❌ Lógica de cálculo de frescura, costo por gramo, conteo de shots.
- ❌ Persistencia activa (la DB está cableada pero ningún screen escribe todavía).

---

## 3. Stack técnico

| Capa | Tecnología |
|---|---|
| Lenguaje | Kotlin 2.0.20 |
| UI | Jetpack Compose (BOM 2024.09.02) + Material 3 |
| Navegación | Navigation Compose 2.8.1 |
| Persistencia local | Room 2.6.1 (con KSP) |
| Asincronía | Kotlin Coroutines + Flow |
| Imágenes | Coil Compose 2.7.0 |
| Build | Gradle 8.7, AGP 8.5.2, Version Catalog |
| Target | minSdk 24 · targetSdk 34 · JVM 17 |

---

## 4. Estructura de paquetes

```
com.oswaldoguevara.mycoffeelab
├── MyCoffeeLabApp.kt          ← Application class (DB lazy)
├── MainActivity.kt            ← ComponentActivity + tema + nav host
│
├── data/
│   ├── local/
│   │   ├── AppDatabase.kt
│   │   ├── dao/              (CoffeeBeanDao, RecipeDao, ServiceLogDao)
│   │   └── entity/           (CoffeeBeanEntity, RecipeEntity, ServiceLogEntity)
│   └── repository/           (CoffeeBeanRepository, RecipeRepository, ServiceLogRepository)
│
└── ui/
    ├── theme/                (Color.kt, Type.kt, Theme.kt)
    ├── navigation/           (Destinations.kt, MyCoffeeLabApp.kt, MyCoffeeLabNavHost.kt)
    ├── common/components/    (PlaceholderScreen.kt)
    ├── dashboard/            (DashboardScreen.kt)
    ├── beans/
    │   ├── list/             (BeansListScreen.kt)
    │   ├── detail/           (BeanDetailScreen.kt)
    │   └── form/             (BeanFormScreen.kt)
    ├── recipes/
    │   ├── list/             (RecipesListScreen.kt)
    │   ├── detail/           (RecipeDetailScreen.kt)
    │   └── form/             (RecipeFormScreen.kt)
    └── logbook/              (LogbookScreen.kt)
```

La separación sigue **MVVM por feature**: cada caso de uso vive en su propio sub-paquete `ui/<feature>/<accion>/` para escalar sin acumular archivos sueltos.

---

## 5. Cómo abrir y correr

**Prerrequisitos**
- Android Studio Hedgehog (2023.1) o superior
- JDK 17 (Android Studio lo trae embebido)

**Pasos**
1. Clonar este repositorio.
2. Abrir la carpeta raíz `MyCoffeeLab/` desde Android Studio.
3. Esperar a que Gradle sincronice (la primera vez descargará Compose BOM, Room, etc.).
4. Correr la configuración `app` en un emulador con API 24+.

> El Gradle Wrapper incluido es un placeholder simbólico para mantener liviano el ZIP. Si Android Studio pide regenerarlo, basta con `File → Sync Project with Gradle Files` o ejecutar `gradle wrapper --gradle-version 8.7` localmente.

---

## 6. Decisiones de arquitectura

- **Offline-first con Room.** El barista atiende en una cafetería sin Wi-Fi estable, y la app debe funcionar igual.
- **Compose + Material 3.** Sin XML legacy para evitar mantener dos sistemas de UI.
- **Flow en los DAOs.** La UI se mantiene reactiva sin refrescos manuales.
- **Invariantes en transacciones Room.** "Un solo bean activo" (HU-03) y "una receta activa por método" (HU-05) viven dentro de `@Transaction` para evitar estados intermedios visibles.
- **Repositorios delgados.** Aún sin lógica, pero ya colocados para que el día que sumemos un backend, los ViewModels no se enteren.
- **Version Catalog.** Todas las versiones centralizadas en `gradle/libs.versions.toml`.

---

## 7. Roadmap

- **Entrega 4 (Unidad 4):** ViewModels con `StateFlow`, formularios reales con validación, cálculos de frescura y costo, registro de shot < 30 s.
- **Entrega final (Unidad 5):** Pruebas unitarias (JUnit) e instrumentadas (Compose Test), pulido visual, manual de usuario y APK firmada de release.

---

## 8. Licencia y créditos

Trabajo académico — UTEC, 2026. Iconografía vectorial propia. Tipografías del sistema.
