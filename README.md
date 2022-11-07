Final Reality
=============

![http://creativecommons.org/licenses/by/4.0/](https://i.creativecommons.org/l/by/4.0/88x31.png)

This work is licensed under a 
[Creative Commons Attribution 4.0 International License](http://creativecommons.org/licenses/by/4.0/)

Context
-------

This project's goal is to create a (simplified) clone of _Final Fantasy_'s combat, a game developed
by [_Square Enix_](https://www.square-enix.com)
Broadly speaking for the combat the player has a group of characters to control and a group of 
enemies controlled by the computer.

---
# Abstract
## v2
Implement weapon restrictions using Double Dispatch and exceptions when corresponds, along with tests.

## v1
This commit implements a new Weapon subclass (Staff), differentiates between black and white mages implementing the
abstract class AbstractMage and BlackMage and WhiteMage classes. The Main file tests for equals() method correctness
for all new implemented classes. Also, it modifies the exception InvalidStatValueException, adding the bound field that 
serves the purpose of late correction of the out-of-bounds values which fired the exception. 
