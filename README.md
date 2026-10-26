# demo-app

Spring Boot 4.1 (Java 21) + Angular 21, built by Maven into one jar.
The page shows the Maven version of the build, read from `GET /api/info`.

```
demo-app/
  pom.xml        parent: version, plugin versions
  frontend/      Angular; Maven installs Node v24.22.1 in ./node, runs npm ci + build,
                 packs dist/ into a jar under static/
  backend/       Spring Boot; depends on the frontend jar, serves it on port 8081
  .mvn/          wrapper (Maven 3.9.16) + project settings (Maven Central, no mirrors)
```

Run every command from this folder: `.mvn/maven.config` points to `.mvn/settings.xml`
with a path relative to the working directory.

## Build and run

```powershell
.\mvnw.cmd clean package
java -jar backend\target\demo-backend-0.1.0-SNAPSHOT.jar   # http://localhost:8081
```

Dev mode with live reload: run the backend, then in `frontend\` run `npm start`
(http://localhost:4200, `/api` proxied to 8081 by `proxy.conf.json`).

## Versions

One version for all modules, set in the parent POM and copied to the children.

| Step | Command | Example |
|---|---|---|
| Release | `.\mvnw.cmd versions:set -DremoveSnapshot` | 0.1.0-SNAPSHOT to 0.1.0 |
| Next snapshot (patch) | `.\mvnw.cmd versions:set -DnextSnapshot` | 0.1.0 to 0.1.1-SNAPSHOT |
| Next snapshot (minor) | `.\mvnw.cmd versions:set -DnextSnapshot -DnextSnapshotIndexToIncrement=2` | 0.1.0 to 0.2.0-SNAPSHOT |
| Any version | `.\mvnw.cmd versions:set -DnewVersion=1.0.0` | |

Release flow: remove snapshot, build, commit + tag, next snapshot, commit.
