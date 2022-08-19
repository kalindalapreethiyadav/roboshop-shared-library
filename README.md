# roboshop-shared-library

This the shared Library where I store all the common functions of different project diferent pipeline's functions in "Shared Library"

Sharable libraries available to any Pipeline jobs running on this system. These libraries will be trusted, meaning they run without “sandbox” restrictions and may use @Grab.

"WHenevre we using "shared library fucntions" that need to mentioned in "script {}" on pipeline file
}

This makes us "not intruppting the variables or fucntion at jenkins level Global"
Instead we are using SHARED Libraries whethere we decleared the required funcion that can been used by mutiple projects.