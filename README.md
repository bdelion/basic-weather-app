# Welcome to basic-weather-app 👋

[![HitCount](http://hits.dwyl.io/bdelion/basic-weather-app.svg)](http://hits.dwyl.io/bdelion/basic-weather-app) [![Version](https://img.shields.io/badge/version-0.0.1-blue.svg?cacheSeconds=2592000)](https://img.shields.io/badge/version-0.0.1-SNAPSHOT-blue.svg?cacheSeconds=2592000) [![Documentation](https://img.shields.io/badge/documentation-yes-brightgreen.svg)](https://github.com/bdelion/basic-weather-app/wiki) [![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](#)

[![Build Status](https://travis-ci.com/bdelion/basic-weather-app.svg?branch=master)](https://travis-ci.com/bdelion/basic-weather-app)  

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/c661294477af4801929d0abb9e9613cf)](https://www.codacy.com/app/bdelion/basic-weather-app?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=bdelion/basic-weather-app&amp;utm_campaign=Badge_Grade)
[![Codacy Badge](https://api.codacy.com/project/badge/Coverage/c661294477af4801929d0abb9e9613cf)](https://www.codacy.com/app/bdelion/basic-weather-app?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=bdelion/basic-weather-app&amp;utm_campaign=Badge_Coverage) 

[![Maintainability](https://api.codeclimate.com/v1/badges/bd5d2ee7ab717b88cad0/maintainability)](https://codeclimate.com/github/bdelion/basic-weather-app/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/bd5d2ee7ab717b88cad0/test_coverage)](https://codeclimate.com/github/bdelion/basic-weather-app/test_coverage)  

[![codecov](https://codecov.io/gh/bdelion/basic-weather-app/branch/master/graph/badge.svg)](https://codecov.io/gh/bdelion/basic-weather-app)  

[![Coverage Status](https://coveralls.io/repos/github/bdelion/basic-weather-app/badge.svg?branch=master)](https://coveralls.io/github/bdelion/basic-weather-app?branch=master)

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=bdelion_basic-weather-app&metric=alert_status)](https://sonarcloud.io/dashboard?id=bdelion_basic-weather-app) [![Coverage](https://sonarcloud.io/api/project_badges/measure?project=bdelion_basic-weather-app&metric=coverage)](https://sonarcloud.io/dashboard?id=bdelion_basic-weather-app) [![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=bdelion_basic-weather-app&metric=ncloc)](https://sonarcloud.io/dashboard?id=bdelion_basic-weather-app) [![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=bdelion_basic-weather-app&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=bdelion_basic-weather-app) [![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=bdelion_basic-weather-app&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=bdelion_basic-weather-app) [![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=bdelion_basic-weather-app&metric=security_rating)](https://sonarcloud.io/dashboard?id=bdelion_basic-weather-app) [![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=bdelion_basic-weather-app&metric=sqale_index)](https://sonarcloud.io/dashboard?id=bdelion_basic-weather-app) [![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=bdelion_basic-weather-app&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=bdelion_basic-weather-app)

<p>
<a href="https://sourcerer.io/bdelion"><img src="https://sourcerer.io/icons/logo-sharing.svg"height="24px" alt="Sourcerer"></a>
</p>

> `basic-weather-app` est une application Java basique et autonome donnant la météo pour un code postal en France.
> 
> Ce projet remplace [javaBasicTraining](https://github.com/bdelion/javaBasicTraining.git), projet créé lors de la 1ère journée d'une formation Java.
> 
> Cette application fait appel à l'API [Current weather data de openweathermap](https://openweathermap.org/current)

### 🏠 [Homepage](https://github.com/bdelion/basic-weather-app/tree/master)

## Usage

Pour exécuter localement `basic-weather-app`, il faut :

1. tout d'abord télécharger [basic-weather-app-0.0.1.jar](#)
2. exécuter la commande :

    ```sh
    java -jar basic-weather-app-0.0.1.jar
    ```

3. A la question `Veuillez saisir le code postal de la ville dont vous souhaitez avoir la météo :` il suffit de saisir un code postal, par exemple `44000` pour obtenir les réusltats suivants :

    ```sh
    =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    Ville : Nantes
    Code postal : 44000
    Pays : FR
    ================
    Base : stations
    Localisation : longitude : -1.55 / latitude : 47.22
    ================
    Temps : Clouds / overcast clouds
    Température : 11,73 °C
    Température min : 11,00 °C
    Température max : 13,00 °C
    Humidité : 93,00
    Vent : 4,60 m/sec

    =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    ```

## Fabriqué avec

* [Visual Studio Code](https://code.visualstudio.com/) - Editeur de code source.
* [Maven](https://maven.apache.org/) - Outil de gestion et d'automatisation de production des projets logiciels.
* [Travis CI](https://travis-ci.com/) - Logiciel libre d'intégration continue.
* [Codacy](https://www.codacy.com/) - Outil d'analyse de code : qualité, compléxité, duplication et taux de couverture des tests unitaires.
* [Code Climate / Quality](https://codeclimate.com/quality/) - Outil d'analyse de code : qualité, maintenabilité, duplication et taux de couverture des tests unitaires.
* [Code Coverage](https://codecov.io/) - Outil d'analyse de la couverture de tests.
* [Coveralls](https://coveralls.io/) - Outil d'analyse de la couverture de tests.
* [SonarCloud](https://sonarcloud.io/about) - Service en ligne d'analyse de qualité et de sécurité du code.

## Authors

👤 **Bertrand DELION**

* Github: [@bdelion](https://github.com/bdelion)

Voir aussi la liste des [contributeurs](https://github.com/bdelion/basic-weather-app/graphs/contributors) ayant participés à ce projet.

## :books: Journal des modifications

Pour connaître les dernières évolutions et leurs impacts, consuler la page [CHANGELOG](CHANGELOG.md)

## 🤝 Contributions

Les contributions, problèmes et demandes de fonctionnalités sont les bienvenus !
N'hésitez pas à consulter la page des [issues](https://github.com/bdelion/basic-weather-app/issues), et à ouvrir une `issue` afin de discuter de ce que vous souhaitez modifier.

## Versioning

Nous utilisons [SemVer](http://semver.org/) pour le versioning. Pour les versions disponibles, voir [les tags de ce projet](https://github.com/bdelion/basic-weather-app/tags).

## Liens utiles

* Documentation : [Wiki](https://github.com/bdelion/basic-weather-app/wiki)
* Build : [Jobs Travis CI](https://travis-ci.com/bdelion/basic-weather-app/builds)
* Repository : [GitHub packages](https://github.com/bdelion/basic-weather-app/packages)
