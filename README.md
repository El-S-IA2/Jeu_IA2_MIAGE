# Jeu_IA2_MIAGE

<p align="center">
  <a href="#"><b>🏆 Projet Dev Jeu IA et joueur </b></a>
  <br><br>
</p>
<p align="center">
  <img  src="https://github.com/El-S-IA2/Jeu_IA2_MIAGE/blob/main/BYSCH!%20(3).png ">
</p>





# Hey  <img src="https://media.giphy.com/media/hvRJCLFzcasrR4ia7z/giphy.gif" width="25px"> <br /> 


 Conception et implémentation d'un jeu (type Awalé)(https://www.regledujeu.fr/awale/). 
 développé avec <img src="https://upload.wikimedia.org/wikipedia/fr/thumb/2/2e/Java_Logo.svg/1200px-Java_Logo.svg.png"  width="30px">  <br /> 
 Ce README résume le travail effectué,
 Projet réalisé par Mohamed Benabdelkrim & Said Elarays, étudiants en MIAGE M2 IA2 (2021/2022).
 
 # Points forts - ✨

- Jeu Facilement extensible pour de nouvelles fonctionnalités 
- Les joueurs peuvent visualiser le plateau sur une interface dans le terminal
- Toutes les règles de jeu sont implémentées


## Points faibles -🤢

- Manque de tests
- L'IA prend un peu de temps pour jouer
 
 
     
##  ✅  le projet :

## Dans le fichier src 6 classes ont été implémenté pour réaliser notre projet : 
	1.Color 
	2.History
	3.Jeu
	4.Main
	5.Mouvement
	6.Partie
	
	
	
	
## Class Color 
> il s'agit uniquement d'une class contenant un Enum qui represente les 2 couleur e graines possible dans le jeu (Red | Blue) . 

 ## Class History 
 
> Création d'une variable globale qui sera utile et agira comme un historique pour notre  minimax fonction; 
> elle permet d'assurer une complexité en espace linéaire par rapport à la profondeur du minimax.

## Class Jeu 
c'est le moteur de jeu de notre po$rojet, on y retrouve les differentes etapes de jeu (choix de mouvement, deplacement, phase de capture, comptage de points, verification des regles et des contraintes, MinMax, fin de jeu...)

```java
L'ensemble des règles du jeu sont implémentées grâce aux fonctionnalités suivantes :
	1.legalMouvements : 	
	2.applyMouvement : 
	3.minimax :  
	4.gameOver : 
```

## Class Main 
>Permet de lancer l'application . 
>elle instancie une partie et lance un jeu de partie

## Class Mouvement 
> Class qui représente un mouvement : prend en entrée un coup, exprimé par PC ou p est la Position et C la couleur de graine à jouer exemple : 2R || 6B etc...

## Class Partie 

> Permets de lancer une partie du jeu  : le jeu se lance avec  une initialisation de jeu et un moteur de jeu avec ajoue d'une fonction 
  qui permet de choisir quel type de joueur commence  à savoir (le robot || le player).
  
## Ajout de librairie externe ➕
Une librairie  a ete installée et implantée dans le jeu : [Jansi](https://github.com/fusesource/jansi)

c'est une petite bibliothèque Java qui vous permet d'utiliser des codes d'échappement ANSI pour formater la sortie de console (output) et permet un affichage avec couleur , même sous Windows(systeme d'exploitatin de nos pc).                                      

## Regles 📜 :
![alt text](https://github.com/El-S-IA2/Jeu_IA2_MIAGE/blob/main/Capture%20d%E2%80%99%C3%A9cran%202022-03-10%20153418.png?raw=true)

| Regle |
| ------ | 
| plateau de 16 trous, 8 par joueur | 
| l'ors d'un tour de jeu, On tourne dans le sens des aiguilles d'une montre |
| deux couleurs de graines : rouge et bleu | 
| Joueur 1 : il joue dans les trous numeroté impair (1,3,7..) | 
| Joueur 2 : il joue dans les trous numeroté pair (2,4,6...) |
| graines rouges  : distribution dans chaque trou suivant  | 
| graines  bleues : distribution uniquement dans les trous de l'adversaire | 
| capture : le nombre de graines d'un trou à exactement deux ou trois graines (de n'importe quelle couleur)| 
| Si l'avant-dernière graine a également amené un trou à deux ou trois graines, celles-ci sont également capturées, et ainsi de suite|

## Condition d'arret de jeu 🛑 :


## Le jeu s'arrête :
-	 lorsqu'il y a strictement moins de 8 graines sur le plateau. Dans ce cas, les graines restantes ne sont pas prises en compte.
-	 lorsqu'un joueur a capturé 33 graines ou plus
-	 lorsque chaque joueur a pris 32 graines (égalité)
-	 'lorsque un joueur ne peut plus jouer dans ces troues (vide partout)



Le gagnant est le joueur qui a plus de graines que son adversaire.

## Petite capture du jeu 🎬 :
![alt text](https://github.com/El-S-IA2/Jeu_IA2_MIAGE/blob/main/unknown.png?raw=true)



## Pour lancer le projet 🔥 :

soit depuis un IDE, aller dans la classe Main et faire un run.

soit à l'aide du fichier jar ↪️ en Bat pour une execution rapide:

```java

un fichier jar du nom de Jeu_IA2_MIAGE_jar   est fournit  :  \Jeu_IA2_MIAGE\out\artifacts\Jeu_IA2_MIAGE_jar
un fichier bat du nom de  jeu.bat est fournit  dans  : \Jeu_IA2_MIAGE\out\artifacts\Jeu_IA2_MIAGE_jar 
   
```


<p align="center">
      🔚 -------- fin du document --------🔚
</p>
               


