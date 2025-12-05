# Upgrade to Java 21 (LTS)

This file explains how to upgrade your workspace to use Java 21 (LTS). The project in this repo is a simple VS Code Java project (no Maven/Gradle detected). Follow these steps.

## 1) Install a JDK 21 distribution

- Recommended builds: Eclipse Temurin (Adoptium), Azul Zulu, or Oracle JDK.
- Download and install for Windows (example: Temurin 21):

  - Visit: https://adoptium.net/ and download the Windows x64 JDK 21 installer, then run it.

## 2) Configure environment (temporary session)

Open PowerShell (`pwsh.exe`) and run (adjust path to your JDK install):

```powershell
# Example for session-only change (replace path with your installation)
$env:JAVA_HOME = 'C:\Program Files\Java\jdk-21'
$env:Path = "$env:JAVA_HOME\bin;" + $env:Path
java -version
javac -version
```

If `java -version` shows `21`, the session is using JDK 21.

## 3) Persistently set JAVA_HOME (optional)

To set `JAVA_HOME` permanently for your user:

```powershell
setx JAVA_HOME "C:\Program Files\Java\jdk-21"
# You'll need to open a new terminal for setx to take effect.
```

## 4) Tell VS Code about the JDK (workspace-level)

The workspace `.vscode/settings.json` contains a `java.configuration.runtimes` entry template. Edit it and replace the `path` value with the actual JDK install path on your machine. Example:

```jsonc
"java.configuration.runtimes": [
  {
    "name": "JavaSE-21",
    "path": "C:/Program Files/Java/jdk-21",
    "default": true
  }
]
```

After editing, restart VS Code so the Java extension picks up the new runtime.

## 5) Compiling/running the project manually

This project compiles to `bin/` by default. To compile all `.java` files with Java 21 using PowerShell:

```powershell
# from repository root
Get-ChildItem -Recurse -Filter *.java -Path .\src | ForEach-Object { $_.FullName } |
  % { $_ } |
  %{ & javac --release 21 -d bin -cp "lib/*" -sourcepath src $_ }

# A simpler approach (compiles all .java found under src):
javac --release 21 -d bin -cp "lib/*" (Get-ChildItem -Recurse -Filter *.java -Path .\src | ForEach-Object { $_.FullName })
```

Note: PowerShell's command-line handling may require adjusting quoting; using an IDE build task is easier.

## 6) If you later adopt Maven/Gradle

- Add `maven-compiler-plugin` config or Gradle `java` toolchain to target Java 21.

## 7) Troubleshooting

- If VS Code still uses an older JDK, confirm `java.configuration.runtimes` and `JAVA_HOME` are correct and restart VS Code.
- If compilation fails due to APIs removed/changed, check source code for deprecated internal APIs and update accordingly.

---

If you want, I can:

- Detect the exact JDK path installed on this machine and update `.vscode/settings.json` for you, or
- Add a VS Code task to compile the project using JDK 21.

Tell me which you'd like me to do next.
