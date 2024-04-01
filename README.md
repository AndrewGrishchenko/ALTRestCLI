# ALTRestCLI

A CLI for comparing ALTRepo branches

This program needs [libbranchdiff](https://github.com/AndrewGrishchenko/BranchDiffLib) to work

## Commands
1. branchDiff
    - Usage: `branchDiff <branch1> <branch2> <?fileName>`
    - Parameters:
        - branch1 - first branch name
        - branch2 - second branch name
        - fileName - JSON filename write to; by default set to "response.json"
    - Description: finds packages that are present in the first branch but not in the second branch
2. branchDiffArch
    - Usage: `branchDiffArch <branch1> <branch2> <arch> <?fileName>`
    - Parameters:
        - branch1 - first branch name
        - branch2 - second branch name
        - arch - specified packages' architecture
        - fileName - JSON filename write to; by default set to "response.json"
    - Description: finds packages that are present in the first branch but not in the second branch
3. branchDiffVersion
    - Usage: `branchDiffVersion <branch1> <branch2> <?fileName>`
    - Parameters:
        - branch1 - first branch name
        - branch2 - second branch name
        - fileName - JSON filename write to; by default set to "response.json"
    - Description: finds packages whose version in the first branch is greater than in the second branch
4. combinedBranchDiff
    - Usage: `combinedBranchDiff <branch1> <branch2> <?fileName>`
    - Parameters:
        - branch1 - first branch name
        - branch2 - second branch name
        - fileName - JSON filename write to; by default set to "response.json"
    - Description: doing branchDiff for two branches and vice-versa, and doing branchDiffVersion

## Responses
Most command responses have following structure:
```json
{
  "responseType": "String",
  "branch1": "String",
  "branch2": "String",
  "totalPackages": 0,
  "arches": {
    "String": {
      "length": 0,
      "packages": [ {
        "name": "String",
        "epoch": 0,
        "version": "String",
        "release": "String",
        "arch": "String",
        "disttag": "String",
        "buildtime": 0,
        "source": "String"
      } ]
    }
  }
}
```

But combinedBranchDiff using different structure:
```json
{
  "responses": [ {
    "responseType": "String",
    "branch1": "String",
    "branch2": "String",
    "totalPackages": 0,
    "arches": {
      "String": {
        "length": 0,
        "packages": [ {
          "name": "String",
          "epoch": 0,
          "version": "String",
          "release": "release",
          "arch": "String",
          "disttag": "String",
          "buildtime": 0,
          "ource": "String"
        } ]
      }
    }
  } ]
}
```

## Install

1. You can easily run a built package `java -jar restclient-*.jar`
2. Or you can run on yourself. For this, run `./mvnw spring-boot:run`
