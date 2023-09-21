# Genetic Programming

This is a project I built off of my Genetic Algorithm library. It takes in a set of datapoints and attempts to fit an equation to them.  
The GA library can be found here: https://github.com/rhit-bowerirn/Java-GA/releases/tag/1.0

## How it works
To optimize an expression with an evolutionary algorithm, we need some way of representing it so it can be easily changed with mutation and crossover. A tree structure suits this quite well.

Consider the following expression in prefix notation:   
$(+ \ (-\ 4\ 5) (*\ 2\ 3))$  

In prefix notation operators come before operands, so this evaluates to:  

$(4\ -\ 5)\ +\ (2\ *\ 3)\ =\ -1\ +\ 6\ =\ 5$  

Though it might seem unnecessarily complicated, this notation allows us to represent an expression as a tree, where the operator is the root and the operands are the leaves. 
A tree representation allows us to optimize it with a genetic algorithm. 
We can mutate these trees by simply switching the operation of the node (either an operand or a terminal value) to an equivalent operation, 
and we can crossover two trees by swapping a random subtree from each of them.  

This simulation supports the functions `+`, `-`, `*`, `/`, and `sqrt()`, and it allows terminals of either a variable `x` or an integer between -20 and 20
