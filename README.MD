# Simplified CPU Pipeline Simulator

A project done for a computer architecture course, written in Java. This project simulates a simplified MIPS CPU. You can feed the program a sequence of CPU instructions, and it can display the state of the pipeline registers, as the it moves through the CPU's pipeline'd datapath. 

# Installation

Clone this repo, and run in any Java IDE. 

# Instructions

* Run the program, and it will by default consume a premade array of instructions for demonstration purposes. 
* To provide your own instructions, just create your own array of instructions from the MIPS Instruction Set, and pass it in. 
* Only the following instructions are supported: nop, add, sub, sb and lb