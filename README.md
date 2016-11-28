# VendingMachine
Because we are all just hungry...

## Build
Build Main.java and run to use the vending machine - the owner hasn't been by to restock for a while, so I hope you are not TOO hungry

` (from /src) `

` javac aishasteege/vending_machine/Main.java `

` java aishasteege/vending_machine/Main `

## Tests
It is worth noting that this is my second time using TDD, and my first time trying to split the tests as code was spit into different classes. I imagine that there is a fair bit of improvement that can be made to how that was done. I am not confident that I handled the broken tests correctly when the vending machine suddenly required stock and I modified the tests, or if I broke the RULES. If I broke said RULES, I admit that although I was a rebel, I am hoping to use TDD a lot more to actually learn said RULES.

- JUnit4 was used as included with Eclipse Neon

## Future Work
The UI could be a bit more robust because if the user doesn't empty the coin return or the product dispenser it will get distorted. I had debated on doing something fun with an actual java UI, and may do so in the future. That said, I am considering this project is complete for the purposes of the kata.
