# Modern (software) Development Industrialization

Tutoriel d'introduction à la gestion de dépendances, de versions, et de l'intégration continue (basé sur le dépôt : https://github.com/selabs-ur1/omd). 

## Objectifs du TP

- Utiliser Git pour sauvegarder et collaborer sur le code source de votre projet, au travers des forges telles que Github et Gitlab
- Comprendre le fonctionnement de maven, configurer un projet de développement, utiliser les artifacts et générer des rapports
- Utiliser un système d’intégration continue tel que Sonar, Jenkins et Gitlab CI

## Prérequis : 

Vérifiez que vous avez installé sur votre machine de développement :

- Java (JDK) 17 ou 21+, e.g. [OpenJDK](https://jdk.java.net/archive/) (avec la variable d'environnement JAVA_HOME bien configurée)
- un IDE Java tel qu'Eclipse, IntelliJ IDEA, NetBeans, VS Code, Xcode, etc.

Les commandes indiquées dans ce tutoriel sont prévues pour être exécutées sous Linux. Toutefois, des commandes équivalentes existent sous Windows. Vous pouvez également utiliser [PowerShell](https://docs.microsoft.com/en-us/powershell/scripting/overview?view=powershell-7.1) ou une machine virtuelle (e.g., [VirtualBox](https://www.virtualbox.org/)).


## Partie 1 : Utilisation de Git

Pour cette partie, vous pouvez utiliser à votre convenance soit [la forge Gitlab](https://gitlab2.istic.univ-rennes1.fr/) fournie par la plateforme ISTIC ESIR, soit [GitHub](https://github.com/). 

N'hésitez pas à regarder le profil de votre compte afin d'ajuster les paramètres ou d'ajouter une clé SSH. 

Cette partie est à réaliser en binôme afin de  visualiser au mieux l'utilité d'un git. Pour certaines parties, chaque personne du binôme aura besoin d'effectuer des commandes git sur le dépôt créé (gestion des conflits, pull request, etc.). 

### Prérequis : 

Vérifiez que vous avez installé [Git](https://git-scm.com/download/) sur votre machine de développement.

### Création d'un dépôt git

Commencez par créer un projet java contenant le fichier [`Calculette.java` fourni](./src_Part1_GIT/Calculette.java). Cette classe est voulue très simple afin de pouvoir uniquement tester les fonctionnalités de git. Exécutez ensuite votre programme.

Créez un repository vide via l'interface de GitHub/GitLab en cochant l'option pour générer automatiquement un fichier README.md. Observez ensuite les paramètres et le contenu du fichier `README.md`. Ajoutez votre binôme à ce projet en tant que `Developers`. 

Ouvrez votre terminal et déplacez-vous au niveau de votre dossier contenant votre projet Java. Vous devez maintenant intialiser Git avec la commande `init`. Votre dépôt local est ainsi créé (un répertoire caché `.git` doit apparaitre). 

Ajoutez ensuite tous les fichiers modifiés à Git (dans la staging zone) via la commande : 

    git add . 
    
et réalisez un commit avec un message. 

Reliez votre dépôt local avec le dépôt distant créé via GitLab (ou GitHub),

    git remote add origin https://gitlab2.istic.univ-rennes1.fr/LoginOuGroupe/nomDepot.git

puis mettez votre code sur ce dépôt : 

    git push -u origin main

Recherchez à quoi correspond l'option `-u` de la commande `push`.

Vous pouvez avoir plusieurs erreurs liés à vos commandes git : 
- votre dépôt local possède une branche `master` alors que votre dépôt distant à sa branche principale appelée `main` (vérifiez le nom de votre branche avec la commande `git branch`). Dans ce cas-là, vous pouvez modifier le nom de votre branche locale. 
- votre dépôt distant contient déjà un fichier, vous devez le récupérer avant de réaliser le `push` à l'aide par exemple de la commande : `git pull origin main`. Vous aurez peut-être besoin de rajouter l'option : `--allow-unrelated-histories`. 

Retournez sur votre dépôt GitLab. Vous pouvez maintenant y observer votre commit et les objets associés. Que remarquez-vous ? 

Vous allez devoir mettre en place un fichier `.gitignore` à la racine de votre projet. Vous pouvez vous inspirer de [ce fichier](https://github.com/github/gitignore/blob/main/Java.gitignore).

Voyez-vous une différence dans votre dépôt GitLab après avoir réalisé un commit/push du fichier .gitignore ?

Normalement non, car les fichiers concernés par ce fichier sont déjà présents dans l'index (la zone de staging). Pour remédier à cela et avoir un dépôt git plus propre, vous pouvez réaliser les commandes suivantes : 

    git rm -r --cached .
    git add . 
    git commit -m "Suppression des fichiers ignorés par le .gitignore"

Observez maintenant les fichiers présents dans votre dépôt GitLab. 

Vous avez maintenant un projet plus propre et votre dépôt local est bien relié avec le dépôt distant. Normalement, l'initialisation d'un dépôt git est assez simple, vous pouvez même le faire graphiquement via votre IDE ou sinon directement sur GitLab ou GitHub en ouvrant votre projet avec VSCode comme le montre la capture ci-dessous.

![OpenProject-GitLab](assets/GitLab_OpenIDE.png)

Lorsque vous créez un nouveau projet sur GitLab/GitHub, vous pouvez le relier avec un nouveau dossier sur votre ordinateur. 

### Clonage d'un dépôt git

Votre binôme souhaite maintenant travailler sur le même projet. Il peut alors cloner le dépôt existant. 

En ouvrant le terminal dans son dossier de travail, il peut écrire la commande suivante : 

    git clone https://gitlab2.istic.univ-rennes1.fr/LoginOuGroupe/nomDepot.git
    cd nomDepot

Son dépôt local correspond maintenant au dépôt distant. 

### Gestion de branches 

Chaque personne du binôme peut réaliser cette partie sur son propre dépôt local. 

Réaliser les instructions suivantes : 
- Création d'une nouvelle branche. Veuillez choisir un nom correct.
- Déterminer cette nouvelle branche comme branche courante (le HEAD doit pointer sur cette branche). 
- Effectuer la commande `git branch` pour vérifier les branches disponibles et la branche courante. 
- Effectuer plusieurs commits en ajoutant quelques fonctionnalités au fichier `Calculette.java` (une fonction multiplication pour une personne et une fonction division pour l'autre personne du binôme par exemple).
- Pousser la branche courante vers le dépôt distant à l'aide de la commande suivante : `git push -u origin nomNouvelleBranche`. Les modifications seront uniquement visibles sur cette branche (et non la branche main).

Vos deux branches modifiées sur le dépôt distant, vous allez essayer de les fusionner avec la branche main. 

Pour la première branche créée, essayez de réaliser la commande `merge`. Pour cela, déplacez-vous sur la branche `main`, récupérez les modifications de cette branche si besoin avec la commande `pull`, puis réalisez le merge de la branche1. 

Observez le résultat. N'hésitez pas à utiliser la commande `git status`. Est-ce que la branche que vous avez fusionnée avec la branche principale existe toujours ? Pensez à la supprimer de votre dépôt local grâce à la commande `git branch -d nomBranche`, puis du dépôt distant avec `git push origin --delete nomBranche`. 

Maintenant que cette première branche est fusionnée, vous allez vous occuper de la deuxième branche, celle créée par votre binôme. C'est donc au tour de votre binôme de la fusionner avec la branche `main`. Il ne va pas pouvoir utiliser directement la commande `merge` sur la branche principale comme celle-ci doit être normalement protégée. 

Commencez par regarder dans les paramètres de votre projet et plus particulièrement les règles de protection des branches. 

![ProtectionBranches-GitLab](assets/GitLab_ProtectedBranches.png)

Comme votre binôme doit avoir le rôle de `developer` et non de `maintener`. Il va devoir alors réaliser une merge/pull request directement via le site de GitLab ou GitHub. Votre binôme doit demander une merge request en donnant une description et en vous l'affectant. 

Vous allez ensuite devoir vérifier ses modifications. Vous pouvez envoyer des commentaires sur la merge request avant de l'accepter. 

Regardez ensuite les branches disponibles sur votre dépôt distant et sur votre dépôt local. Que remarquez-vous ?

Regardez la documentation associée au [pruning](https://git-scm.com/docs/git-fetch#_pruning) afin de comprendre la commande `git fetch origin --prune` ou `git remote prune origin`. En retour de cette commande, vous allez connaître la branche qui a été supprimée. Si elle existe toujours dans votre dépôt local (git branch), vous devez l'enlever manuellement.

### Gestion des conflits 

Vous allez essayer de créer des conflits :
- Créez une nouvelle branche et modifiez le nom de la fonction réalisant la division ainsi que son contenu. 
- Votre binôme va aussi réaliser des modifications de cette fonction (ajout de commentaires, etc.), mais cette fois-ci sur la branche `main`. Il effectue un commit et push afin d'envoyer les modifications sur le dépôt distant.
- C'est à votre tour de tenter un merge avec la branche `main` (pour rappel, vous êtes sur la nouvelle branche créée). Commencez par récupérer les informations de la branche principale via la commande `git push origin main`. Normalement, git va signaler un conflit. 
- Résolvez le conflit en éditant le fichier en conflit et en enlevant manuellement les <<<<>>>>. 
- Commitez ensuite les modifications.

Une bonne pratique : avant de créer une nouvelle branche, mettez toujours à jour votre branche locale avec les dernières modifications de `main`.

### Diverses commandes 

Vous allez maintenant essayer d'utiliser les commandes de git suivantes : 
- git rebase
- git revert
- git reset
- git commit --amend
- git blame

Pour git rebase, vous devez créer une nouvelle branche et y ajouter une modification. Votre binome doit réaliser un commit/push sur la branche main en ajoutant par exemple des commentaires. Réalisez un commit de votre modification dans la nouvelle branche, puis effectuez la commande `git rebase`. Une fois les conflits gérés, observez l'historique des commits. 

Réalisez ensuite un nouveau commit lié à l'ajout d'une nouvelle fonction dans le fichier java. Vous avez malheureusement réalisé une erreur dans le code de cette fonction, et vous souhaitez donc annuler le commit. Utilisez la commande `git log` pour retrouver l'identifiant du commit, puis la commande `git revert idCommit` pour annuler ce commit sans supprimer l'historique. Observez pour terminer votre historique. 

Regardez ensuite la documentation pour les trois dernières commandes. 

> N'hésitez pas à en tester d'autres !
> Le site https://learngitbranching.js.org/?locale=fr_FR vous propose un tutoriel avec une bonne visualisation de l'impact sur l'historique de certaines commandes git.

## Partie 2 : Utilisation de Maven

### Liens utiles

- Site de Maven : http://maven.apache.org/
- FAQ MAVEN developpez.com : http://java.developpez.com/faq/maven/

### Prérequis : 

Vérifiez que vous avez téléchargé et installé sur votre machine de développement [Maven](http://maven.apache.org/install.html). Pensez à bien configurer vos variables d'environnement comme indiqué dans le lien. 

### Environnement 

Maven est essentiellement un outil de gestion et de compréhension de projet. Maven offre des fonctionnalités de : construction, compilation ; documentation ; rapport ; gestion des dépendances ; gestion des sources ; mise à jour de projet ; déploiement.

Utiliser Maven consiste à définir dans chaque projet à gérer un script Maven appelé POM : *pom.xml*. Nous allons voir dans ce TP qu'un POM permet de définir des dépendances, des configurations pour notamment construire, tester, mettre en paquet des artefacts logiciels (exécutables, tests, documentations, archives, etc.). Pour cela, Maven récupère sur des dépôts maven les outils dont il a besoin pour exécuter le POM. Utiliser Maven requière donc : une (bonne) connexion à Internet car il télécharge beaucoup de choses ; de l'espace disque pour la même raison. Les artefacts qu'il télécharge sont originellement stockés dans le dossier *.m2* dans votre home-dir. Ce dossier contient également le fichier de configuration Maven : settings.xml.

Pour configurer Maven de manière à changer l'endroit où les artefacts téléchargés seront stockés (e.g., afin d'éviter des problèmes d'espace disque), vous pouvez modifier le fichier settings.xml de la manière suivante :

    <?xml version="1.0" encoding="UTF-8"?>
    <settings>
        <localRepository>/tmp/mavenrepository</localRepository>
        <offline>false</offline>
    </settings>

### Création d'un projet Maven

Création d’une application basique : pour initialiser un projet Java, vous pouvez utiliser l’archetype maven *maven-archetype-quickstart*. Vous avez juste à fournir un *groupId* et un *artefactId*.

Dans Eclipse:

    new -> other -> maven -> maven project. Vous devrez sélectionner l’archetype,  l’artifactId et le groupId

En ligne de commande (non nécessaire si vous l’avez fait depuis Eclipse):

    mvn archetype:generate \
        -DgroupId=[your project's group id] \
        -DartifactId=[your project's artifact id] \
        -DarchetypeArtifactId=maven-archetype-quickstart

Ou simplement :

    mvn archetype:generate \
        -DgroupId=[your project's group id] \
        -DartifactId=[your project's artifact id]

Ou encore dans VSCode : 

    CommandPalet -> Maven: New Project. Vous devez ensuite sélectionner l'archetype, sa version, le groupID et l'artifactID.

Vous obtenez la structure de projet jointe :

    |-- src
    |   |-- main
    |   |   `-- java
    |   |       `-- [your project's package]   
    |   |           `-- App.java
    |   `-- test
    |       `-- java
    |           `-- [your project's package]   
    |               `-- AppTest.java
    `-- pom.xml

Par exemple, si vous exécutez la commande :
    
    mvn archetype:generate \
        -DgroupId=fr.esir.omd.ci \
        -DartifactId=tpmaven

Vous obtiendrez l’architecture suivante :
 
    |-- src
    |   |-- main
    |   |   `-- java
    |   |       `-- fr
    |   |           `-- esir
    |   |               `-- omd
    |   |                   `-- ci
    |   |                       `-- tpmaven
    |   |                           `-- App.java
    |   `-- test
    |       `-- java
    |           `-- fr
    |               `-- esir
    | 	                `-- omd
    |		                `--ci
    |                           `-- tpmaven
    |                               `-- AppTest.java
    `-- pom.xml

Le fichier pom.xml est le fichier de configuration maven du projet. Il décrit les caractéristiques du projet (son nom, sa famille, sa version, etc.), ainsi que les processus (les « builds ») à exécuter (la compilation, l'exécution des tests, la création d'archive, etc.).

Il existe différentes tâches Maven de base, i.e. fournies par Maven. Les principales sont :
- `mvn clean`: supprime le dossier target. Le dossier target d'un projet maven contient toutes les données produites par maven (classes compilées, jar produits, rapports, etc.) ;
- `mvn compile`: lance la compilation du code source du projet Maven (mais pas la compilation des tests) ;
- `mvn test`: mvn compile + lance la compilation et l'exécution des tests ;
- `mvn package`: mvn test + lance les opérations de packaging (exemple : la création de fichiers jar) ;
- `mvn install`: mvn package + installe les jar produits dans le dossier .m2 de l'utilisateur pour une utilisation dans les autres projets en local.

Chaque plugin configuré et utilisé dans un pom peut fournir des tâches spécifiques.

### Configuration d'un projet Maven dans Eclipse

Depuis Eclipse 4.X, le support de maven s’est amélioré. Pour importer votre projet (ne pas faire si vous avez créé votre projet maven depuis eclipse, ce sera par contre à faire après avoir récupéré un projet depuis un serveur git) : 

    File -> import -> maven -> existing maven project.

Votre projet est configuré.

### Utilisation de git 

Créez un dépôt git et reliez le au projet maven. À chaque nouvelle fonctionnalité, essayez d'effectuer un commit. Vous êtes libre de créer des branches. Cela vous permettra d'être, par la suite, plus à l'aise avec git. 

## Partie 2 : Gestion des dépendances

Intégrez à votre projet le contenu du dossier [*src_Part2_Maven*](./src_Part2_Maven/) dans le dossier *src/main/java/fr/esir/omd/ci* de votre projet maven (le chemin dépend du groupId que vous avez utilisé).

Vous verrez que le code ne compile pas car il manque trois dépendances. Intégrez maintenant les dépendances [gson](https://mvnrepository.com/artifact/com.google.code.gson/gson), [commons CSV](https://mvnrepository.com/artifact/org.apache.commons/commons-csv) et [itextpdf](https://mvnrepository.com/artifact/com.itextpdf/itextpdf) dans le fichier _pom.xml_, et plus particulièrement à l'intérieur des balises _\<dependencies>...\<dependencies>_.

    <!-- Gson pour gérer les fichiers JSON -->
    <dependency>
        <groupId>com.google.code.gson</groupId>
        <artifactId>gson</artifactId>
        <version>2.11.0</version>
    </dependency>

    <!-- Apache Commons CSV pour gérer les fichiers CSV -->
    <dependency>
        <groupId>org.apache.commons</groupId>
        <artifactId>commons-csv</artifactId>
        <version>1.12.0</version>
    </dependency>

    <!-- iText pour générer le PDF -->
    <dependency>
        <groupId>com.itextpdf</groupId>
        <artifactId>itextpdf</artifactId>
        <version>5.5.13.4</version>
    </dependency>

Votre IDE va télécharger la dépendance et la mettre automatiquement dans votre classpath. Dans ce sens, cela permet de ne mettre dans votre gestionnaire de source que le code source et le descripteur de projet (pom.xml). 

## Partie 3 : Spécialisation du processus de build

Imaginons que vous souhaitiez ajouter une tâche dans le processus de build. Par exemple, compiler votre code source avec la version Java 21. Ajoutez la section suivante à votre fichier pom.xml (essayez en changeant de version) :

    <build>
        <plugins>
          <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <configuration>
              <!-- or whatever version you use -->
              <source>21</source>
              <target>21</target>
            </configuration>
          </plugin>
        </plugins>
    </build>

Attention, le plugin peut déjà être présent dans votre fichier *pom.xml*. Vous devez alors le compléter. 

Comme Maven privilégie la configuration donnée par le plugin `maven-compiler-plugin`, vous pouvez supprimer, dans les balises *properties* du *pom.xml*, les balises indiquant la version du JDK (ou alors indiquez bien la même version).

    <properties>
        <maven.compiler.source>21</maven.compiler.source>
        <maven.compiler.target>21</maven.compiler.target>
        ....
    </properties>

Vous pouvez ajouter de nombreux plugins dans cette section. Prenez le temps d'aller regarder [ici](https://maven.apache.org/plugins/).

Utilisez la commande `mvn clean compile` pour compiler votre projet maven. 

## Partie 4 : Ajout de nouvelles dépendances 

Vous allez maintenant modifier le code afin d'ajouter une interface graphique. Elle va permettre de saisir le nom de la tâche et sa description, puis de l'ajouter soit dans un fichier csv, soit dans un fichier json, soit dans un fichier pdf. 

Récupérez les fichiers de code présent dans le dossier [src_Part4_Maven](./src_Part4_Maven/). 

Copiez le fichier [*TaskView.fxml*](./src_Part4_Maven/TaskView.fxml) dans le dossier *src/main/resources/fr/esir/omd/ci* (emplacement dépendant de votre groupeId de votre projet maven), et les autres fichiers dans le dossier *src/main/java/fr/esir/omd/ci/*. Le code présent dans ces fichiers montrent un exemple, le code n'est pas optimisé, commenté et testé. Une tâche par exemple ne peut pas être à la fois enregistrée en cvs et en json. Vous allez compléter et modifier le projet par la suite.

Le projet utilise maintenant [JavaFX](https://openjfx.io/) pour créer et utiliser une interface graphique. 

Vous devez insérer les dépendances et plugins associés dans votre fichier *pom.xml*. Dans la section *dependancies* de votre projet, ajoutez [JavaFX Controls](https://mvnrepository.com/artifact/org.openjfx/javafx-controls) et [JavaFX FXML](https://mvnrepository.com/artifact/org.openjfx/javafx-fxml). Vous pouvez choisir la version 23.0.1.

Puis dans la section *plugins* de *build* : 

    <!-- Plugin pour compiler JavaFX -->
    <plugin>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-maven-plugin</artifactId>
        <version>0.0.8</version>
        <!-- Default configuration for running with: mvn clean javafx:run -->
        <configuration>
            <mainClass>fr.esir.omd.ci.App</mainClass>
        </configuration> 
    </plugin>

Testez le programme via la commande `mvn clean javafx:run`. 

## Partie 5 : Ajout de Log

Comme vu en cours, il existe plusieurs bilbiothèques permettant de gérer les logs de votre projet. Dans ce tutoriel, nous allons ajouter [*SLF4J*](https://mvnrepository.com/artifact/org.slf4j/slf4j-api) avec [*Logback*](https://mvnrepository.com/artifact/ch.qos.logback/logback-classic).

Dans la partie dépendance de votre fichier *pom.xml*, ajoutez : 

    <!-- SLF4J et Logback pour la journalisation -->
    <dependency>
        <groupId>ch.qos.logback</groupId>
        <artifactId>logback-classic</artifactId>
        <version>1.5.12</version>
    </dependency>

Configurez ensuite Logback pour définir les niveaux de log et le format des messages. Dans le répertoire *src/main/resources/*, ajoutez le fichier *logback.xml* contenant : 

    <configuration>

        <!-- Appender pour la console -->
        <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
            <!-- encoders are assigned the type
                ch.qos.logback.classic.encoder.PatternLayoutEncoder by default -->
            <encoder>
                <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} -%kvp- %msg%n</pattern>
            </encoder>
        </appender>

        <!-- Appender pour le fichier texte -->
        <appender name="ROLLING" class="ch.qos.logback.core.rolling.RollingFileAppender">
            <file>logs/app.log</file>
            <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
                <fileNamePattern>logs/logFile.%d{yyyy-MM-dd_HH:mm:ss}.log</fileNamePattern>
                <maxHistory>30</maxHistory>
                <totalSizeCap>20GB</totalSizeCap>
            </rollingPolicy>
            <encoder>
                <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} -- %msg%n</pattern>
            </encoder>
        </appender>

        <!-- Logger root pour rediriger les logs vers les deux appenders -->
        <root level="DEBUG">
            <appender-ref ref="STDOUT" />
            <appender-ref ref="ROLLING"/>
        </root>

    </configuration>

Vous allez maintenant intégrer des logs dans les classes de votre projet. 

Par exemple, dans la classe `TaskManager`, ajoutez une donnée membre : 

    private static final Logger logger = LoggerFactory.getLogger(TaskManager.class);

Dans les méthodes de cette classe, vous allez pouvoir utiliser des fonctions comme :

    loger.info("Nouvelle tâche ajoutée {}", task.getTitle())
    loger.debug("Récupération de la liste des {} tâches", tasks.size())

Réfléchissez aux endroit où mettre les logs et au niveau de gravité (trace, debug, info, warn, error par exemple).

## Partie 6 : Génération de rapports

### Générer la Javadoc

Commencez par écrire des commentaires à votre code (de type /** */). Ensuite, ajoutez ou mettez à jour le code suivant dans la section _build_ du pom.xml de votre projet.

    <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-site-plugin</artifactId>
        <version>3.21.0</version>
    </plugin>
    <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-project-info-reports-plugin</artifactId>
        <version>3.8.0</version>
    </plugin>

Puis dans la nouvelle section _reporting_ (à mettre en dehors de la section _build_) :

    <reporting>
      <plugins>
       <plugin>
    	<groupId>org.apache.maven.plugins</groupId>
    	<artifactId>maven-javadoc-plugin</artifactId>
    	<version>3.11.1</version>
       </plugin>
     </plugins>
    </reporting>

Lancez en ligne de commande (au même niveau que le fichier *pom.xml*) : _mvn site_. Cette tâche crée un site Web pour votre projet. Par défaut, les goals maven générant des fichiers travaillent dans le dossier target se trouvant au même niveau que le fichier *pom.xml*. Allez dans le dossier *target/site* et ouvrez le fichier *index.html*. Vous pouvez regarder la Javadoc générée en cliquant sur *Project reports*.

### Valider la qualité du code avec Checkstyle

Ajoutez à la section \<plugins> dans \<reporting> le plugin [checkstyle](http://maven.apache.org/plugins/maven-checkstyle-plugin/) :

    <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-checkstyle-plugin</artifactId>
	    <version>3.6.0</version>
    </plugin>

Lancez *mvn clean site* (le goal clean vide le dossier target). Sur la page Web générée par « mvn site », une nouvelle section Checkstyle a été ajouté dans Project reports.

Quelle est la norme de codage à laquelle se réfère le rapport par défaut ? Comment imposer la norme de codage de Google ? Le fichier de configuration de Google est inclus dans checkstyle vous devez juste indiquer dans la configuration que vous souhaitez l’utiliser (cf. https://maven.apache.org/plugins/maven-checkstyle-plugin/examples/custom-checker-config.html). Modifiez votre classe du projet de façon à diminuer le nombre d'erreurs. 

- Site de l'outil CheckStyle : http://checkstyle.sourceforge.net/

### Rapport croisé de source

Ajoutez à la section \<plugins> dans \<reporting> le plugin [jxr](http://maven.apache.org/plugins/maven-jxr-plugin/) :

    <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-jxr-plugin</artifactId>  
        <version>3.0.0</version>
    </plugin>	

Quelle est la valeur ajoutée de ce plugin ? En particulier, montrez sa complémentarité avec CheckStyle.
Désormais vous pouvez passer du rapport CheckStyle au code source en cliquant sur le numéro de ligne associé au commentaire CheckStyle.

### Couverture des tests avec JaCoCo

À quel point les développeurs ont réalisé des tests unitaires ? Quelles parties de l'application n'ont pas été testées ?

Ecrivez quelques tests en JUnit (_/tpmaven/src/test/java/fr/esir/omd/ci/TaskManagerTest.java_ par exemple), et voyez quelle couverture de code vous obtenez. 

	<build>
		<plugins>
			<plugin>
                <groupId>org.jacoco</groupId>
                <artifactId>jacoco-maven-plugin</artifactId>
                <version>0.8.12</version>
                <executions>
                    <execution>
                        <id>default-prepare-agent</id>
                        <goals>
                            <goal>prepare-agent</goal>
                        </goals>
                        <configuration>
                            <destFile>target/jacoco.exec</destFile>
                        </configuration>
                    </execution> 
                    <execution>
                        <id>report</id>
                        <phase>prepare-package</phase>
                        <goals>
                            <goal>report</goal>
                        </goals>
                        <configuration>
                            <dataFile>target/jacoco.exec</dataFile>
                            <outputDirectory>target/jacoco-report</outputDirectory>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
		</plugins>
	</build>
    
	<reporting>
		<plugins>
			<plugin>
				<groupId>org.jacoco</groupId>
				<artifactId>jacoco-maven-plugin</artifactId>
				<reportSets>
					<reportSet>
						<reports>
							<!-- select non-aggregate reports -->
							<report>report</report>
						</reports>
					</reportSet>
				</reportSets>
			</plugin>
		</plugins>
	</reporting>

Lien utile : https://www.eclemma.org/jacoco/

Vous pouvez utiliser par exemple la commande *mvn clean test site* et vérifier si la documentation javadoc a bien été générée. 

### Identifier des patterns d'erreur avec PMD

Ajoutez volontairement du code mort à votre code (e.g., une méthode non utilisée) et identifiez le code mort (e.g., variables ou paramètres non utilisés) et la duplication de code (e.g., code copié/collé = possible bug copié/collé, code 'compliqué', e.g., trop de if...else ...).

Ajoutez à la section \<reporting> le plugin [PMD](https://maven.apache.org/plugins/maven-pmd-plugin/) :

    <project>
        <reporting>
            <plugins>
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-pmd-plugin</artifactId>
                    <version>3.26.0</version>
                    <configuration>
                        <linkXref>true</linkXref>
                        <minimumTokens>100</minimumTokens>
                    </configuration>
                </plugin>
            </plugins>
        </reporting>
    </project>

Quels sont les deux nouveaux rapports générés ? A quoi correspond le 'CPD Report' et le 'PMD Report' ?

### Obtenir l'activité du projet

Combien et quels fichiers ont été modifiés par un développeur ? Vous avez dû normalement réaliser plusieurs commits sur votre dépôt. 

Ajoutez à la section \<reporting> le plugin changelog, et définissez la section \<scm> en fonction de votre application et gestionnaire de version :
    
    <project>

        <scm>
            <connection>scm:git:https://your-git-server.com/your-repo.git</connection>
            <url>https://your-git-server.com/your-repo</url>
        </scm>

        <build>
            <plugins>
                <!-- Plugin SCM -->
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-scm-plugin</artifactId>
                    <version>2.1.0</version>
                </plugin>
                <!-- Plugin changelog -->
                <plugin>
                    <groupId>se.bjurr.gitchangelog</groupId>
                    <artifactId>git-changelog-maven-plugin</artifactId>
                    <version>2.2.0</version>
                    <executions>
                        <!-- Template from file // -->
                        <execution>
                            <id>GenerateGitChangelogTemplateFromFile</id>
                            <phase>generate-sources</phase>
                            <goals>
                                <goal>git-changelog</goal>
                            </goals>
                            <configuration>
                                <useIntegrations>true</useIntegrations>
                                <templateFile>src/main/resources/changelog.mustache</templateFile>
                                <readableTagName>-([^-]+?)$</readableTagName>
                                <file>CHANGELOG.md</file>
                            </configuration>
                        </execution>
                    </executions>
                </plugin> 
            </plugins>
        </build>

    </project>


Pour compléter cette partie, créez un fichier *changelog.mustache* dans le dossier *src/main/resources*. Aidez vous de la [documentation du pluging](https://github.com/tomasbjerre/git-changelog-maven-plugin) pour créer votre template. Voici un exemple :

    # Changelog

    Changelog for {{ownerName}} {{repoName}}.

    {{#tags}}
    ## {{name}}
    {{#issues}}
    {{#hasIssue}}
    {{#hasLink}}
    ### {{name}} [{{issue}}]({{link}}) {{title}} {{#hasIssueType}} *{{issueType}}* {{/hasIssueType}} {{#hasLabels}} {{#labels}} *{{.}}* {{/labels}} {{/hasLabels}}
    {{/hasLink}}
    {{^hasLink}}
    ### {{name}} {{issue}} {{title}} {{#hasIssueType}} *{{issueType}}* {{/hasIssueType}} {{#hasLabels}} {{#labels}} *{{.}}* {{/labels}} {{/hasLabels}}
    {{/hasLink}}
    {{/hasIssue}}
    {{^hasIssue}}
    ### {{name}}
    {{/hasIssue}}

    {{#commits}}
    **{{{messageTitle}}}**

    {{#messageBodyItems}}
    * {{.}}
    {{/messageBodyItems}}

    [{{hash}}](https://gitlab2.istic.univ-rennes1.fr/{{ownerName}}/{{repoName}}/commit/{{hash}}) {{authorName}} *{{commitTime}}*

    {{/commits}}

    {{/issues}}
    {{/tags}}

Lancez _mvn clean package site_ par exemple. Que s'est t'il passé dans vos dossiers et dans la documentation html de votre projet ?

> Vous allez dans la suite étudier les outils d'intégration continue Sonar et Jenkins/GitLab CI. La différence entre ces deux outils est simple : Sonar est un outil d'assurance qualité tandis que Jenkins est un outil de « release engineering ». Les deux sont complémentaires.

## Partie 7 : Intégration avec l'outil Sonar

Téléchargez [Sonar](https://www.sonarsource.com/products/sonarqube/downloads/), décompressez-le dans */tmp*, puis lancez dans un terminal Sonar : 

Pour linux : 

    sh ./bin/linux-x86-64/sonar.sh start

Pour windows : 

    .\StartSonar.bat

> Attention, le dossier */tmp* est un dossier temporaire. Le contenu peut-être effacé entre deux séances de TP. Vous pouvez installer Sonar où vous le souhaitez. 

Vous pouvez avoir plusieurs types d'erreurs. Plus de détails sont donnés dans le terminal et dans les fichiers logs de Sonar.

Les erreurs communes que vous pouvez rencontrer : 
- *UnsupportedOperationException: The Security Manager is deprecated and will be removed in a future release* : Vérifiez que votre version du jdk est bien compatible avec votre version de Sonar. Vous allez devoir sans doute utiliser jdk 17 et non jdk 21+. Vérifiez vos variables d'environnement JAVA_HOME et path.
- Vérifiez si vous avez les droits admin pour lancer Sonar. 
- *Address already in use: bind* : le port 9000, celui par défaut pour maven peut déjà être utilisé. Vous pouvez le changer dans les settings de Sonar. 
- *org.elasticsearch.bootstrap.StartupException: java.lang.IllegalStateException: failed to obtain node locks* : vérifiez que vous avez assez d'espace disque sur votre ordinateur. Si c'est le cas et que vous avez toujours cette erreur, vous pouvez essayer de vider le contenu des dossiers : data et tmp.

Une fois que Sonar est opérationnel, vous pouvez aller à l'adresse http://localhost:9000/ sur un navigateur web. Loguez-vous avec le login _admin_ et le mot de passe _admin_. Vous devez changer ce dernier. Ne l'oubliez pas ! 

Cliquez sur le menu *Administration*, *Securité* puis *user* et créez un token pour votre utilisateur. Ne l'oubliez également pas. 

Au niveau du pom de votre projet, lancez la commande *mvn sonar:sonar* dans un autre terminal. 

Vous allez obtenir cette erreur : 
    Not authorized. Please check the user token in the property 'sonar.token' or the credentials in the properties 'sonar.login' and 'sonar.password'.

Complétez la commande *mvn sonar:sonar* en conséquence. 

Allez dans Quality Profiles et changez les règles de qualités utilisées puis relancez _mvn sonar:sonar_. Baladez-vous dans Sonar pour explorer ces différentes fonctionnalités. 

Vous pourrez ensuite arrêter Sonar avec
    
    sh ./bin/linux-x86-64/sonar.sh stop

## Partie 8 : Intégration avec Jenkins

Sur http://jenkins-ci.org/, prenez la version LTS Java Web Archive (.war) pour la mettre dans */tmp*. Il faut déplacer l'endroit où la configuration Jenkins sera stockée :

    export JENKINS_HOME=/tmp/.jenkins

Démarrez [Jenkins](https://www.jenkins.io/doc/book/installing/war-file/) : 

    java -jar jenkins.war --httpPort=9900 
    
Allez dans votre navigateur : http://localhost:9900/.

Continuez la configuration de Jenkins en suivant les étapes jusqu'à l'affichage du tableau de bord. 

Dans Jenkins :
- Allez dans le menu « Jenkins → Manage Jenkins → Global Tool Configuration »
Cliquez sur « Add JDK ». Saisissez un nom quelconque permettant d'identifier la JDK et l'emplacement du JDK si ce dernier se trouve sur votre machine.
- Cliquez sur « Add Maven ». Saisissez un nom quelconque permettant d'identifier cette version de Maven. Vous allez devoir sans doute cliquer sur la checkbox *Install automatically* si cette dernière est déjà cochée. Vous pourrez ainsi saisir le chemin de maven dans la variable *MAVEN_HOME*. 
- Cliquez sur « Save ». Le but de ces configurations est de pouvoir installer, si on le souhaite, plusieurs Maven ou plusieurs JDK (certains projets peuvent nécessiter différentes versions de Java).

Vérifiez ensuite l'installation des modules suivants : module git pour Jenkins : « Jenkins → Manage Plugins → Available → « GIT plugin » et « Maven integration plugin ». Ces plugins peuvent être déjà installés.

Ensuite créez un « job » en cliquant sur « create new job -> Maven Project ». Donnez un nom à votre projet. 

Définissez les sources en indiquant l'url du repository git que vous avez préalablement créé sur GitLab ou GitHub (i.e. https://github.com/login/nomRepo.git). Vous pouvez également configurer la connexion git (en créant un token) avec le plugin GitLab ou GitHub  (« Jenkins → Admin  → System »).

Définissez enfin les goals maven pour le build (« Add build step » → « Invoke top-level Maven... ») : pour commencer _clean package_. Si le pom n’est pas à la racine de votre projet, cliquez sur « Advanced... » → remplissez le champ POM. 

Lancer un build. N'hésitez pas à cliquer sur le build en cours d'exécution, puis sur la partie *Sortie de la console* pour avoir des informations supplémentaires sur le build. Le premier va être un peu long puisqu'il télécharge les dépendances et plugins de maven. 

Dans l'historique des builds, une icône bleue ou verte doit apparaître à la fin de la construction pour désigner la construction correcte de l'artefact (bleu car le développeur de Jenkins est Japonais et au Japon le bleu équivaut au vert chez nous). Cliquez ensuite sur le lien sous « Module builds », les artefacts créés par Jenkins en utilisant le POM du projet sont visibles dont un jar. Ouvrez ce dernier, vous verrez que le manifest est vide. Dans les étapes suivantes vous allez compléter le POM pour obtenir un vrai jar exécutable.

### Packager des artefacts logiciels avec maven

Les artefacts logiciels peuvent être produits soit en utilisant maven en ligne de commande (ou au travers de l'IDE), soit en utilisant un outil d'intégration continue tel que Jenkins. Dans cette partie, nous allons étudier différents plugins maven permettant de réaliser des actions liées à la construction d'artefacts logiciels, et qu'il faudra automatiser à l'aide de Jenkins. 

#### Création d'un jar exécutable via maven

Pour construire des artefacts vous allez ajouter un bloc \<build> dans le bloc \<project> de votre POM. Générez un jar exécutable grâce au _plugin maven-jar-plugin_ qui vous permettra de définir un manifest :
http://maven.apache.org/plugins/maven-jar-plugin/ (regardez les exemples « creating an executable JAR file »). N'oubliez pas qu'il faut inclure en plus dans le .jar toutes les dépendances du projet ([JavaFX](https://medium.com/@kennydop/how-to-create-an-executable-jar-file-for-javafx-using-maven-f8a0039de1fa), Gson, etc.). Pour cela, un autre plugin peut être necéssaire. 

Lancez _mvn clean install_ et vérifiez le fichier manifest. Exécutez le nouveau jar généré se trouvant dans le dossier target. Commitez et pushez vos changements, relancez le build Jenkins, allez dans le « last build » et cliquez sur le « Module Builds » listé : la liste des éléments produits doit être visible et téléchargeable.

#### Création d'archives des sources et des exécutables

Le [plugin _maven-assembly-plugin_](http://maven.apache.org/plugins/maven-assembly-plugin/) permet de créer des archives. Ce plugin est notamment très utile pour créer des archives des sources ou des fichiers exécutables (voir aussi: https://medium.com/@kasunpdh/using-the-maven-assembly-plugin-to-build-a-zip-distribution-5cbca2a3b052).

Étudiez et adaptez l'utilisation de ce plugin dans le projet suivant : https://github.com/latexdraw/latexdraw/blob/master/pom.xml pour l'utiliser dans votre projet afin de créer un zip des sources et un autre contenant le jar exécutable.

Commitez les modifications sur le Git et relancez un build sur Jenkins afin d'observer les évolutions apportées. 

#### Exécution de test via maven

Utilisez le [plugin _maven-surefire-plugin_](http://maven.apache.org/surefire/maven-surefire-plugin/) pour exécuter les tests du projet si ce n'est pas déjà fait lors de la commande _mvn clean install_. 
Commitez le POM sur le Git (avec quelques tests) et relancez un build sur Jenkins afin d'observer les évolutions apportées.

### Utilisation de Sonar, JaCoCo et PMD

- https://plugins.jenkins.io/sonar/
- https://plugins.jenkins.io/jacoco/
- https://plugins.jenkins.io/warnings-ng/

Attention pour Cobertura, vous avez besoin de définir le format de sortie en xml.
Pour cela, il existe deux solutions:
- la première consiste à ajouter une option dans la définition du build maven: “-Dcobertura.report.format=xml”
- la deuxième consiste à modifier la configuration dans votre pom et d’ajouter l’option de configuration appropriée (voir sur la page de Cobertura plugin)

### Daily/Nightly build avec Jenkins

Configurer vos builds Jenkins pour qu'ils se construisent automatiquement à 1h du matin tous les jours.

## Partie 8 : Intégration avec GitLab CI

Pour cette partie, votre projet devra être hébergé sur GitLab (e.g., https://gitlab2.istic.univ-rennes1.fr), avec la permission d'exécuter des pipelines (Settings / General / Permissions / Pipelines). Cette permission doit déjà être activée. 

Quelques définitions préliminaires des concepts de [GitLab CI](https://docs.gitlab.com/ee/ci/) : 
- [pipeline](https://docs.gitlab.com/ee/ci/pipelines/) : un ensemble de _jobs_ (quoi faire?), chacun à réaliser lors d'un _stage_ (quand le faire?).
- [runner](https://docs.gitlab.com/runner/) : GitLab utilise des runners sur différents serveurs pour exécuter les jobs
dans un pipeline. GitLab fournit des runners à utiliser, mais vous pouvez aussi utiliser vos propres serveurs comme runners.
- [jobs](https://docs.gitlab.com/ee/ci/jobs/) : tâche à exécuter dans un pipeline.
- [stage](https://docs.gitlab.com/ee/ci/yaml/#stages) : un groupe de jobs connexes à exécuter dans un pipeline.

### Installation de votre runner 

Pour installer votre runner, veillez vous référer à la [documentation](https://docs.gitlab.com/runner/install/). Vous pouvez l'installer en local, ou via un conteneur (e.g. docker). 

Vous allez devoir [créer un runner avec un token d'authentification](https://docs.gitlab.com/ee/ci/runners/runners_scope.html#create-a-project-runner-with-a-runner-authentication-token). Dans votre projet GitLab, déplacez vous dans « Settings > CI/CD > Runners ». Vous pouvez désactiver le runner par défaut de GitLab (https://docs.gitlab.com/ee/ci/runners/index.html), puis sélectionner _New project runner_. Saisissez les différentes informations, créer le runner, puis choississez votre plateforme (Linux, Windows, Docker, etc.).

#### En local pour Windows ou Linux 

Dans un terminal / powershell, terminez les instructions indiquées :

Pour Windows :

    # Run PowerShell: https://docs.microsoft.com/en-us/powershell/scripting/windows-powershell/starting-windows-powershell?view=powershell-7#with-administrative-privileges-run-as-administrator
    # Create a folder somewhere on your system, for example: C:\GitLab-Runner
    New-Item -Path 'C:\GitLab-Runner' -ItemType Directory
    # Change to the folder
    cd 'C:\GitLab-Runner'
    # Download binary
    Invoke-WebRequest -Uri "https://gitlab-runner-downloads.s3.amazonaws.com/latest/binaries/gitlab-runner-windows-amd64.exe" -OutFile "gitlab-runner.exe"

ou pour Linux : 

    # Download the binary for your system
    sudo curl -L --output /usr/local/bin/gitlab-runner https://gitlab-runner-downloads.s3.amazonaws.com/latest/binaries/gitlab-runner-linux-amd64
    # Give it permission to execute
    sudo chmod +x /usr/local/bin/gitlab-runner
    # Create a GitLab Runner user
    sudo useradd --comment 'GitLab Runner' --create-home gitlab-runner --shell /bin/bash

Vous pouvez installer gitlab-runner à l'emplacement de votre choix. 

Enregistrez ensuite le runner avec les informations demandées (https://gitlab2.istic.univ-rennes1.fr, le runner authentication token, etc.). Pour l'executor, choisissez soit _docker_ ou _shell_.

Installez et démarrez le runner. 

Pour windows : 

    .\gitlab-runner.exe install
    .\gitlab-runner.exe start

Pour Linux : 

    # Install and run as a service
    sudo gitlab-runner install --user=gitlab-runner --working-directory=/home/gitlab-runner
    sudo gitlab-runner start

#### En local, via docker: 

Installer un runner docker sur votre pc en suivant les instructions : https://docs.gitlab.com/runner/install/docker.html

Lancer votre Runner à l’aide de la commande
sudo docker run -d --name gitlab-runner --restart always -v /srv/gitlab-runner/config:/etc/gitlab-runner -v /var/run/docker.sock:/var/run/docker.sock gitlab/gitlab-runner:latest

Si vous ne possédez pas docker, veuillez suivre les instructions d’installation de Docker pour votre système d’exploitation https://docs.docker.com/get-docker/. 
Installer ensuite le gitlab Runner, puis faire la partie register runner https://docs.gitlab.com/runner/register/?tab=Docker. 

### Configuration de votre runner sur votre projet Gitlab

Une fois l'installation de votre runner terminée, vous devriez le voir apparaître dans votre projet sur Gitlab : « Settings > CI/CD > Runners ».

Vous pouvez re-configurer votre runner. Par exemple, cliquez sur le crayon pour éditer votre runner, puis véifier si la case Run untagged jobs est cochée. Voilà, votre runner est maintenant prêt !

Vous pouvez maintenant explorer le menu « Settings > Integration » et ajouter l’envoi d’un email automatique à votre adresse mail lorsque vous effectuez un push sur le repository, et lorsque votre pipeline de CI/CD obtient une erreur.

### Configuration de votre pipeline

GitLab utilise le fichier ".gitlab-ci.yml" pour faire fonctionner le pipeline de l'Intégration Continue pour chaque projet. Le fichier ".gitlab-ci.yml" doit se trouver dans le répertoire racine de votre projet. 

Créez un fichier ".gitlab-ci.yml" avec le contenu suivant dans votre projet, réalisez ensuite un commit et push. Explorez votre pipeline dans le menu « Build > Pipelines ». 

	job1:
	    stage: build 
	    script:
	    - echo "foo"
	job2:
	    stage: test 
	    script:
	    - echo "bar"
	job3:
	    stage: deploy 
	    script:
	    - echo "foobar"

Cliquez sur un job pour voir le détail de son exécution dans le shell (pratique pour savoir pourquoi un job a échoué).

Voici également [un autre exemple](https://gist.github.com/combemale/fb68a577d91a6594594a145b162aeb9b). Aidez vous de cet exemple et de la [documentation](https://docs.gitlab.com/ee/ci/yaml/) pour définir un pipeline similaire à celui que vous avez défini sur Jenkins dans la partie précédente.
