# Discrete Event Simulator
_Last Updated on March 15, 2020_

### Purpose

A discrete event simulator is a software that simulates a system with events and states, and can be used to study
many complex real-world systems. An event occurs at a particular time, and each event alters the states of the system
and may generate more events. States remain unchanged between two events (hence the term discrete), and this
allows the simulator to jump from the time of one event to another. A simulator typically also keeps track of some
statistics to measure the performance of the system.

The purpose of this project aims to prototype a queuing system comprising a single customer queue and single service point, 
whereby given a set of customer arrival times in chronological order, this will output the discrete events and statistics at the
end of the simulation.

### Development Process

- [x] Level 1
- [x] Level 2
- [x] Level 3
- [x] Level 4
- [x] Level 5

#### Level 1

#### Customer Arrivals

Each arriving customer should be tagged with a customer ID (starting from 1) as well as an arrival time. 
We need to also know the number of customers arriving. 
The user input is simply a list of arrival times without any prior indication of the number of arrivals.

#### Level 2

#### Serving the Customer

A customer that arrives is served immediately, but only if the server is not currently serving; otherwise, the customer leaves. 

#### Level 3

#### Incorporating States 

In this level, we take into consideration customer arrivals, customers being served and customers leaving.

#### Level 4

#### Scheduling Events

As an example, suppose three arrival events are initially scheduled.
# Adding arrivals
0.500 1 arrives
0.600 2 arrives
0.700 3 arrives

The next event to pick is <0.500 1 arrives>. This schedules a serve event.
# Get next event: 0.500 1 arrives
0.500 1 served
0.600 2 arrives
0.700 3 arrives

The next event to pick is <0.500 1 served>. This schedules a done event.
# Get next event: 0.500 1 served
0.600 2 arrives
0.700 3 arrives
1.500 1 done

The process is repeated until there are no more events.
Specifically, pick the earliest occurring one, and if there is a tie, pick the one with the smallest customer ID.

#### Level 5

#### Completion of Discrete Event Stimulation

To complete the discrete event stimulation, we need to consider that an arriving customer can either be served, wait to be served or leaves. Also, compute the statistics at the end of the simulation.

Example of a sample run of a program:

**0.500** (User input)
**0.600** (User input)
**0.700** (User input)
0.500 1 arrives
0.500 1 served
0.600 2 arrives
0.600 2 waits
0.700 3 arrives
0.700 3 leaves
1.500 1 done
1.500 2 served
2.500 2 done
[0.450 2 1]
