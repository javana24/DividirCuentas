package com.alanturing.dividircuentasv1
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun DividircuentasApp(){
    var Money by remember { mutableStateOf("") }
    var People by remember { mutableStateOf("") }

    TextField(
        value = People,
        onValueChange = { newPerson -> People = newPerson },
        modifier = Modifier.fillMaxWidth().padding(10.dp),
        label = {Text("Comensales")},
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        )

    )

    TextField(

        value = Money,
        onValueChange = { newMoney -> Money = newMoney },
        modifier = Modifier.fillMaxWidth().padding(10.dp),
        label = {Text("Cantidad")},
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        )
    )

    var checked by remember { mutableStateOf(false) }
    Row(
        Modifier.fillMaxWidth().padding(15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text("Redondear Propina")
            Switch(
                checked = checked,
                modifier = Modifier.padding(start = 10.dp),
                onCheckedChange = {
                    checked = it
                },
            )
    }
    var sliderPositions by remember { mutableFloatStateOf(0f) }
    Row (
        Modifier.fillMaxWidth().padding(15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Absolute.Center
    ){
        Text("Valoración",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(horizontal = 10.dp))
    }
    Column {
        Slider(
            value = sliderPositions,
            onValueChange = { sliderPositions = it},
            modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
            enabled = checked,
            steps = 4,
            valueRange = 0f..5f
        )


    }

    var Money_: Int? = Money.toIntOrNull()
    var Person: Int? = People.toIntOrNull()
    var Final by remember { mutableStateOf("") }
    Button(

        onClick = {
            if (Person == null){
                Person == 1
            }
            if(Money_ != null && Person != null && Person > 0){
                var propina: Float = 0F
                if(checked == true) {
                    propina = Money_ * ((sliderPositions*5)/100)
                }
                var total = Money_+propina
                var pers = total/Person
                Final = "Cantidad total: $total \nCada uno: $pers"
            }
            else
                Final = "Error"
        },
        modifier = Modifier.fillMaxWidth().padding(15.dp)

    )
    {Text("Calcular")}
    Text(text = Final)


}