# Team Sloths : Projet Génie Logiciel

## General

Cette application correspond au projet de deuxième année de cycle ingénieur en _Technologies web avancée_. Il répond au sujet suivant :

> Chaque année, les élèves du module Génie Logiciel consacrent quelques semaines à la rédaction d’un dossier de spécification suite à des entretiens avec un client attitré. L’objectif de ce projet est de fournir un outil de gestion de ces entretiens depuis la constitution des équipes jusqu’à la restitution des documents exigés par le client en passant par les prises de Rdv.
>
> Cette plateforme permettra à chaque client de rédiger un ou plusieurs sujets et de les assigner à des équipes. Un client aura la possibilité de lister les fonctionnalités de son projet et de les classer par priorité.
>
> Le client peut faire plusieurs réunions avec une équipe donnée, mais ces réunions ne doivent pas dépasser une certaine durée. Durant chaque réunion, le client note ses appréciations sur chaque équipe et coche les fonctionnalités qu’elle aura identifiées. Il peut également ajouter les fonctionnalités supplémentaires proposées par chaque équipe.
>
> Chaque équipe aura la possibilité de rajouter des comptes rendus à la suite de chaque réunion et d’y déposer des documents.

Ce projet a été réalisé pour la conception et le développement par :
* Louis Arbaretier [[GitHub](https://github.com/larbaretier) | [LinkedIn](https://fr.linkedin.com/pub/louis-arbaretier/107/8b7/332)]
* Charles Mariotte [[LinkedIn](https://www.linkedin.com/in/charles-mariotte-207113117/)]
* Hugo Michard [[GitHub](https://github.com/HugoMichard)  | [LinkedIn](https://www.linkedin.com/in/hugo-michard-326922124/)]
* Thibault Vlacich [[GitHub](https://github.com/ThibaultVlacich) | [Twitter](https://twitter.com/ThibaultVlacich) | [LinkedIn](https://fr.linkedin.com/in/thibaultvlacich)]
* Boris Viresolvy [[GitHub](https://github.com/bviresolvy) | [LinkedIn](https://www.linkedin.com/in/boris-viresolvy-5b964212b/)]

## Installation

### Prerequisites

* JDK 8
* [NodeJS 6+ & NPM 4](https://nodejs.org/)

### Let's go

To clone the project and install all dependencies:
```
git clone https://github.com/ThibaultVlacich/Projet-GL-Sloth.git
cd Projet-GL-Sloth
npm install
npm install -g gulp
```

Then, create the file `src/main/resources/application.properties` based on the model of `src/main/resources/application.properties-template` and replace the values according to your settings.

Finaly, to run the app :

#### From Command-line interface
* On UN*X systems (Linux / Mac):
```
npm start
```

* On Windows:
```
npm run start-windows
```

The app should be available under the URL http://localhost:8080/
