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

...

#### Level 5

#### Completion of Discrete Event Stimulation

...
 
