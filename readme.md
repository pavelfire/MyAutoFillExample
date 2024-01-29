I just have one approach but If someone already did this then please let me know your approach.

Read the SMS from native app code then expose to web view so that JavaScript can read from window
object. You can create a waiting function in JavaScript to read a globally exposed variable from App
bridge. Once to get some valid value fill it in the otp text box and clear the value in the global
variable.