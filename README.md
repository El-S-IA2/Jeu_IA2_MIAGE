# Jeu_IA2_MIAGE

<p align="center">
  <a href="#"><b>🏆 Projet Dev Mobile</b></a>
  <br><br>
</p>
<p align="center">
  <img  src="https://github.com/El-S-IA2/Jeu_IA2_MIAGE/blob/main/BYSCH!%20(3).png ">
</p>





# Hey  <img src="https://media.giphy.com/media/hvRJCLFzcasrR4ia7z/giphy.gif" width="25px"> <br /> 


 Conception et implémentation d'une application type Vinted (https://www.vinted.fr/). 
 développé avec Flutter  <img src="https://storage.googleapis.com/cms-storage-bucket/70760bf1e88b184bb1bc.png"  width="50px">  <br /> 
 Celle-ci s’appellera « MIAGED »,
 Ce README résume le travail effectué,
 Projet réalisé par Said Elarays, étudiant en  MIAGE M2 IA2 (2021/2022).
     
##  ✅  le projet :

## 🗓    Délai respecté    

## User Story implémentées avec succès :

 
 - [x] US#1 : [MVP] Interface de login 
 - [x] US#2 : [MVP] Liste de vêtements
 - [x] US#3 : [MVP] Détail d’un vêtement
 - [x] US#4 : [MVP] Le panier
 - [x] US#5 : [MVP] Profil utilisateur
 - [x] US#6 : Filtrer sur la liste des vêtements

## 🌸 Bilan des fonctionnalités supplémentaires  <img src="https://media.giphy.com/media/26vwfMVM6nlEkwftUj/giphy.gif" width="40px">  : 


## Dans le processus d'authentification et de profil :
	
		1. Possibilité de cree un nouveau compte [depuis l'interface login;
		2. Possibilité de compléter so profil (nom, prénom, âge, etc..);
		3. Possibilité de recevoir le MDP en cas d'oubli [depuis l'interface login;
		4. Les pages politiques de confidentialité et paramètre de notification sont accessibles
		   depuis profil.
		
## Dans la page acheter vêtements :

		1. Une barre de recherche située un haut de l'écran permet de rechercher un article
		   parmi tous les articles, recherche par nom article et/ou catégorie de l'article;
		2. Un bouton + est visible en bas à droite de l'écran, il permet une fois cliqué, de 			 		 
		   naviguer vers une page d'ajout d'article où on remplit les diffèrentes 		  
		   info(nom, marque, prix).
		   cet article sera ensuite ajouté dans la base de données et devient visible 

## Dans la page panier :
		

		 1. Possibilité de modifier la quantité de l'article dans le panier, une mise à jour de  	
		    la facture est faite en arrière-plan;
		 2. Un recupilatif de la commande avec les différentes parties de la facture finale est 
		    visible.


## Dans la page Discussion :

	une fonctionnalité discutait est implémenté: un membre peut discuter avec tous les autres 		
	membres de l'application.
	la discussion est stocké avec toutes les donnes(texte, photo, vidéo...).
			

## 🚧 Remarques :

Quelques bugs  peuvent  être  visibles  sur  l'application  mais  rien  de  méchant.
<br /> 
Tout au long du projet, le device de test était un Galaxy S20+ (384 px X 854 px), ainsi pour une meilleure expérience utilisateur, 
il est conseillé de lancer l'application sur cet même device ou avec un autre de résolutions identiques.

## 💁🔑Authentification :
Pour pouvoir se connecter, un identifiant est mis en place :

 - Login/adresse mail : admin@hotmail.fr
 - Mot de passe : admin2021



## 🔧Installation:

Étant donnes qu'une intégration Firestore est dans le projet, vous devez d'abord exécuter les commandes suivantes pour vous assurer que le projet se compile 

```
flutter pub get
flutter packages pub run build_runner build --delete-conflicting-outputs
```
Cette commande crée les fichiers générés qui analysent chaque enregistrement de Firestore dans un objet de schéma.

Un fichier APK [MIAGEDrelease10.apk](MIAGEDrelease10.apk)est aussi mis en place pour pouvoir tester plus rapidement l'application sur un appareil android


<p align="center">
      -------- fin du document --------
</p>
                                                  


Regles :

![alt text](https://github.com/El-S-IA2/Jeu_IA2_MIAGE/blob/main/Capture%20d%E2%80%99%C3%A9cran%202022-03-10%20153418.png?raw=true)


Il y a 16 trous, 8 par joueur
Les trous sont numérotés de 1 à 16. On tourne dans le sens des aiguilles d'une montre : Le trou 1 suit le trou 16 dans le sens des aiguilles d'une montre. 
Le premier joueur a les trous impairs, le deuxième joueur a les trous pairs.
(Attention ceci est très différent de l'oware)

Il y a deux couleurs : rouge et bleu
Au début, il y a 2 graines de chaque couleur par trou.

-- Objet
Le jeu commence avec 2+2 graines dans chaque trou. Le but du jeu est de capturer plus de graines que son adversaire. Comme il y a un nombre pair de graines, il est possible que le jeu se termine par un match nul, où chaque joueur a capturé 32 graines.

-- Semer
Les joueurs déplacent les graines à tour de rôle. À son tour, un joueur choisit l'un des trous de hauteur qu'il contrôle. Le joueur retire les graines de ce trou (voir ci-dessous pour la gestion des couleurs), et les distribue, en en déposant une dans les trous dans le sens des aiguilles d'une montre (c'est-à-dire dans un ordre non décroissant) à partir de ce trou, dans un processus appelé semis. 
Les déplacements se font en fonction des couleurs. D'abord une couleur est désignée et toutes les graines de cette couleur sont jouées, 
Si les graines sont rouges, alors elles sont distribuées dans chaque trou. Si les graines sont bleues, alors elles sont distribuées uniquement dans les trous de l'adversaire.

Les graines ne sont pas distribuées dans le trou tiré. Le trou de départ est toujours laissé vide ; s'il contenait 16 graines (ou plus), il est sauté, et la seizième graine est placée dans le trou suivant. 
Ainsi, un coup est exprimé par NC où N est le numéro du trou, C est la couleur qui est jouée.
Par exemple, 3R signifie que l'on joue les graines rouges du trou 3 (et seulement les rouges). 

-- Capturer
La capture ne se produit que lorsqu'un joueur porte le nombre de graines d'un trou à exactement deux ou trois graines (de n'importe quelle couleur). Cela permet toujours de capturer les graines du trou correspondant, et éventuellement plus : Si l'avant-dernière graine a également amené un trou à deux ou trois graines, celles-ci sont également capturées, et ainsi de suite jusqu'à ce qu'un trou ne contenant pas deux ou trois graines soit atteint. Les graines capturées sont mises de côté. Affamer l'adversaire EST AUTORISÉ
Attention, il est permis de prendre les graines de son propre trou et les graines sont capturées indépendamment de leurs couleurs.
Prendre toutes les graines de l'adversaire est autorisé. En cas d'affamage, toutes les graines sont capturées par le dernier joueur.
Le jeu s'arrête lorsqu'il y a strictement moins de 8 graines sur le plateau. Dans ce cas, les graines restantes ne sont pas prises en compte.

-- Gagner
La partie est terminée lorsqu'un joueur a capturé 33 graines ou plus, ou que chaque joueur a pris 32 graines (égalité), ou qu'il ne reste strictement moins de 8 graines. Le gagnant est le joueur qui a plus de graines que son adversaire.



Exemples :
 
Cas 1 :
 
1 (2R)
16 (2R) 15 (2B) 14 (2B2R) 13 (2R2B)
 
Le joueur pair joue 14 B
L'ensemencement conduit à
1(2R1B)
16 (2R) 15 (3B) 14 (2R) 13 (2R2B)
 
Donc, les graines de 1, 16, 15, 14 sont prises.
Le résultat est ;
1()
16() 15() 14 () 13 (2R2B) . Le joueur pair a pris 10 graines
 
Cas 2 :
1 (1R) 2 (2R) 3(1B) 4(2B) 5(1R)
16 (3B1R) 15 (2R) 14 (4B)
 
Cas 2.1 :
Le joueur pair joue 16B
 
L'ensemencement conduit à
1 (1B1R) 2 (2R) 3(2B) 4(2B) 5(1B1R)
16 (1R) 15 (2R) 14 (4B)
Les trous 5,4,3,2,1 sont capturés. 10 graines sont capturées
Les résultats sont
1 () 2 () 3 () 4 () 5 ()
16 (1R) 15 (2R) 14 (4B)
 
Cas 2.2 :
Le joueur pair joue 16R
 
L'ensemencement conduit à
1 (2R) 2 (2R) 3(1B) 4(2B) 5(1R)
16 (3B) 15 (2R) 14 (4B)
 
Les trous 1, 16, 15 sont capturés 2+3+2=7 graines sont capturées
Le résultat est :
1 () 2 (2B) 3(1B) 4(2B) 5(1R)
16 () 15 () 14 (4B)


## Pour lancer le projet :


```java

un fichier jar du nom de Jeu_IA2_MIAGE_jar   est fournit  :  \Jeu_IA2_MIAGE\out\artifacts\Jeu_IA2_MIAGE_jar
un fichier bat du nom de  jeu.bat est fournit  dans  : \Jeu_IA2_MIAGE\out\artifacts\Jeu_IA2_MIAGE_jar 
   
```



