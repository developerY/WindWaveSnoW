import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ylabz.windwatersnow.network.model.Clouds
import com.ylabz.windwatersnow.network.model.Coord
import com.ylabz.windwatersnow.network.model.Main
import com.ylabz.windwatersnow.network.model.OpenWeatherResponse
import com.ylabz.windwatersnow.network.model.Snow
import com.ylabz.windwatersnow.network.model.Sys
import com.ylabz.windwatersnow.network.model.Weather
import com.ylabz.windwatersnow.network.model.Wind
import com.ylabz.windwatersnow.wind.ui.components.WeatherConditions.RainAnimationScreen
import com.ylabz.windwatersnow.wind.ui.components.WeatherConditions.SnowfallAnimation
import com.ylabz.windwatersnow.wind.ui.components.WeatherConditions.SunshineAnimationScreen
import com.ylabz.windwatersnow.wind.ui.components.WeatherConditions.WaveAnimationScreenSet
import com.ylabz.windwatersnow.wind.ui.components.WeatherConditions.WindAnimation
import com.ylabz.windwatersnow.wind.ui.components.cards.Waves.WaveHeightCardAI
import com.ylabz.windwatersnow.wind.ui.components.cards.Wind.WindDirectionCardAI
import com.ylabz.windwatersnow.wind.ui.components.cards.snow.SnowVolumeCardAI
import com.ylabz.windwatersnow.wind.ui.components.cards.temp.TemperatureCardAI


@Composable
fun WeatherCardsAI(openWeatherResponse: OpenWeatherResponse?, title: String) {
    val backgroundColor = getBackgroundColorByTitle(title)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(backgroundColor)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            WeatherLocationCard(location = title)
            Spacer(modifier = Modifier.height(16.dp))

            when (title) {
                "Surfer Wave" -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        Column {
                            WaveHeightCard(height = Double.MIN_VALUE)
                            Spacer(modifier = Modifier.height(16.dp))
                            WindSpeedCard(openWeatherResponse?.wind?.speed ?: 0.0)
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                        WaveAnimationScreenSet()
                    }
                }
                "Snowboard Mt." -> {
                    Box {
                        Column {
                            TemperatureCard(temp = openWeatherResponse?.main?.temp ?: 0.1)
                            Spacer(modifier = Modifier.height(16.dp))
                            WindSpeedCard(speed = openWeatherResponse?.wind?.speed ?: 0.0)
                        }
                        SnowfallAnimation()
                    }
                }
                "Rain" -> {
                    Box {
                        Column {
                            SnowVolumeCardAI(volume = openWeatherResponse?.snow?.`1h` ?: Double.NaN)
                            Spacer(modifier = Modifier.height(16.dp))
                            WaveHeightCard(height = Double.NaN)
                            Spacer(modifier = Modifier.height(16.dp))
                            WindSpeedCard(speed = openWeatherResponse?.wind?.speed ?: 0.0)
                        }
                        RainAnimationScreen()
                    }
                }
                "Sunshine" -> {
                    Box {
                        Column {
                            SnowVolumeCard(volume = openWeatherResponse?.snow?.`1h` ?: Double.NaN)
                            Spacer(modifier = Modifier.height(16.dp))
                            WaveHeightCard(height = Double.NaN)
                            Spacer(modifier = Modifier.height(16.dp))
                            WindSpeedCard(speed = openWeatherResponse?.wind?.speed ?: 0.0)
                        }
                        SunshineAnimationScreen()
                    }
                }
                "Sailor Wind" -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Column {
                            WindDirectionCardAI(openWeatherResponse?.wind?.deg ?: 0)
                            TemperatureCardAI(temp = openWeatherResponse?.main?.temp ?: 0.1)
                            Spacer(modifier = Modifier.height(16.dp))
                            WindSpeedCard(speed = openWeatherResponse?.wind?.speed ?: 0.0)
                            Spacer(modifier = Modifier.height(16.dp))
                            WindDirectionCard(deg = openWeatherResponse?.wind?.deg ?: 0)
                            Spacer(modifier = Modifier.height(16.dp))
                            WaveHeightCardAI(height = Double.NaN)
                        }
                        WindAnimation()
                    }
                }
                else -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Column {
                            TemperatureCard(temp = openWeatherResponse?.main?.temp ?: 0.1)
                            Spacer(modifier = Modifier.height(16.dp))
                            WindSpeedCard(speed = openWeatherResponse?.wind?.speed ?: 0.0)
                            Spacer(modifier = Modifier.height(16.dp))
                            WindDirectionCard(deg = openWeatherResponse?.wind?.deg ?: 0)
                            Spacer(modifier = Modifier.height(16.dp))
                            WaveHeightCard(height = Double.NaN)
                        }
                    }
                }
            }
        }
    }
}



@Composable
fun WeatherLocationCard(location: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .shadow(8.dp, RoundedCornerShape(8.dp)),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = location,
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun TemperatureCard(temp: Double) {
    // State to toggle between Celsius and Fahrenheit
    var isCelsius by remember { mutableStateOf(true) }
    val temperature = if (isCelsius) temp else temp * 9 / 5 + 32
    val unit = if (isCelsius) "°C" else "°F"

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .shadow(8.dp, RoundedCornerShape(8.dp))
            .clickable {
                isCelsius = !isCelsius
            },
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFC107))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Temperature",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "$temperature $unit",
                    style = MaterialTheme.typography.displayMedium,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun WindSpeedCard(speed: Double) {
    // Infinite transition for wind icon sliding animation
    val infiniteTransition = rememberInfiniteTransition()
    val translationX by infiniteTransition.animateFloat(
        initialValue = -100f,  // Start from outside the left edge
        targetValue = 500f,    // End outside the right edge
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 5000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .shadow(8.dp, RoundedCornerShape(8.dp)),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF00BCD4))
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Wind Speed",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "${speed} m/s",
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)  // Ensure the box has a fixed height for the animation
            ) {
                Row() {

                    Text(
                        text = "\uD83C\uDF2C", // Wind emoji
                        fontSize = 32.sp,
                        modifier = Modifier
                            .graphicsLayer(translationX = translationX)
                        //.align(Alignment.CenterVertically)
                    )

                    Icon(
                    imageVector = Icons.Default.Air,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .size(64.dp)
                        .graphicsLayer(translationX = translationX)
                )

                }
            }
        }
    }
}



@Composable
fun WindDirectionCard(deg: Int) {
    val infiniteTransition = rememberInfiniteTransition()
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 4000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .shadow(8.dp, RoundedCornerShape(8.dp)),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF4CAF50))
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Wind Direction",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Icon(
                imageVector = Icons.Default.Navigation,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .size(64.dp)
                    .graphicsLayer(rotationZ = rotation)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "$deg°",
                style = MaterialTheme.typography.displayMedium,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun SnowVolumeCard(volume: Double) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .shadow(8.dp, RoundedCornerShape(8.dp)),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF9C27B0))
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Snow Volume",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "$volume mm",
                style = MaterialTheme.typography.displayMedium,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun WaveHeightCard(height: Double) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .shadow(8.dp, RoundedCornerShape(8.dp)),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF2196F3))
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Wave Height",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "$height m",
                style = MaterialTheme.typography.displayMedium,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun getBackgroundColorByTitle(title: String): Color {
    return when (title) {
        "Sailor Wind" -> Color(0xAEE3F3F1)
        "Surfer Wave" -> Color(0xAEB8D0E7)
        "Snowboard Mt." -> Color(0xFF9A988B)
        "Rain" -> Color(0xD2D9E8E2)
        "Sunshine" -> Color(0x5BFFC107)
        else -> Color(0xFFA3A3A3) // default color
    }
}



val sampleWeatherResponseExample = OpenWeatherResponse(
    coord = Coord(lon = 10.0, lat = 20.0),
    weather = listOf(
        Weather(id = 800, main = "Clear", description = "clear sky", icon = "01d")
    ),
    base = "stations",
    main = Main(
        temp = 15.0,
        feels_like = 13.0,
        temp_min = 10.0,
        temp_max = 20.0,
        pressure = 1013,
        humidity = 50
    ),
    visibility = 10000,
    wind = Wind(speed = 5.0, deg = 200, gust = 7.0),
    clouds = Clouds(all = 0),
    rain = null,
    snow = Snow(`1h` = 5.0, `3h` = 10.0),
    dt = 1633035600,
    sys = Sys(
        type = 1,
        id = 1234,
        country = "US",
        sunrise = 1632996000,
        sunset = 1633039200
    ),
    timezone = -14400,
    id = 5128581,
    name = "New York",
    cod = 200
)

@Preview(showBackground = true, name = "Wind")
@Composable
private fun WindContentPreview() {
    WeatherCardsAI(openWeatherResponse = sampleWeatherResponseExample, title = "Sailor Wind")
}

@Preview(showBackground = true, name = "Snow")
@Composable
private fun SailorWindContentPreview() {
    WeatherCardsAI(openWeatherResponse = sampleWeatherResponseExample, title = "Snowy Mountain")
}

@Preview(showBackground = true, name = "Sunny")
@Composable
private fun RainContentPreview() {
    WeatherCardsAI(openWeatherResponse = sampleWeatherResponseExample, title = "Snowy Mountain")
}
