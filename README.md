# Yet another optimization challenge: the 0–1 knapsack problem and the hard instances

## Problem description

The [0–1 knapsack](https://www.sciencedirect.com/science/article/pii/S0925231223007531) problem (KP01) is a classical NP-hard combinatorial optimization problem.
KP01 has many applications in real-world complex problems such as resource distribution, portfolio optimization, etc.
In KP01 problem, one is given a knapsack with an _integer_ capacity and a **set** of  items, which each have an integer profit and an integer weight.
The goal is to select a subset of items to put into the knapsack such that the total value is maximized and the total weight does not exceed the knapsack capacity.

The task is to Design and implement the following(s)

Phase 1:  Create java class(es) to represent a 0–1 knapsack problem instance given in the format described in the [GitHub repository](https://github.com/JorikJooken/knapsackProblemInstances).
A dataset of 3240 problem instances is made publicly available by [Jooken, Leyman, and De Causmaecker (2022)](https://www.sciencedirect.com/science/article/pii/S037722172101016X). 
Each problem instance is distributed as a text file named *test.in*.
The first line of the file represents the number of items, *n*. Each of the *n* following lines describe an item and contains 3 integers: 
* the id of the item, 
* the profit of the item and 
* the weight of the item. 
The last line contains an integer describing the knapsack capacity, *c*.

For example, if the file contains the following:

```
3
1 3 8
2 2 8
3 9 1
10
```

This describes a problem instance in which there are n=3 items and the knapsack has a capacity of c=10. The item with id 1 has a profit of 3 and a weight of 8.
The item with id 2 has a profit of 2 and a weight of 8. The item with id 3 has a profit of 9 and a weight of 1.

