package com.example.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.ui.theme.FeatherGreen
import com.example.ui.theme.FeatherGreenDark

/**
 * Duolingo-style tactile 3D button with a darker bottom lip that depresses cleanly when pressed.
 */
@Composable
fun DuolingoButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    containerColor: Color = FeatherGreen,
    lipColor: Color = FeatherGreenDark,
    contentColor: Color = Color.White,
    tag: String = ""
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val lipHeight = 4.dp
    val pressOffset by animateDpAsState(
        targetValue = if (isPressed && enabled) lipHeight else 0.dp,
        animationSpec = spring(stiffness = 700f),
        label = "btnOffset"
    )

    val effectiveContainer = if (enabled) containerColor else MaterialTheme.colorScheme.outline.copy(alpha = 0.45f)
    val effectiveLip = if (enabled) lipColor else MaterialTheme.colorScheme.outline.copy(alpha = 0.7f)
    val effectiveContent = if (enabled) contentColor else MaterialTheme.colorScheme.onSurfaceVariant

    Box(
        modifier = modifier
            .minimumInteractiveComponentSize()
            .height(56.dp)
            .then(if (tag.isNotEmpty()) Modifier.testTag(tag) else Modifier)
            .clickable(
                enabled = enabled,
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
    ) {
        // Bottom 3D lip
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .align(Alignment.BottomCenter)
                .clip(RoundedCornerShape(18.dp))
                .background(effectiveLip)
        )

        // Top tactile face
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .offset(y = pressOffset)
                .clip(RoundedCornerShape(18.dp))
                .background(effectiveContainer),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.ExtraBold,
                color = effectiveContent
            )
        }
    }
}

/**
 * Tactile rounded card with crisp border and bottom 3D lip, bouncing smoothly on tap.
 */
@Composable
fun TactileCard(
    onClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.surface,
    borderColor: Color = MaterialTheme.colorScheme.outline,
    lipColor: Color = MaterialTheme.colorScheme.outline,
    cornerRadius: Dp = 20.dp,
    tag: String = "",
    content: @Composable BoxScope.() -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed && onClick != null) 0.985f else 1f,
        animationSpec = spring(stiffness = 650f),
        label = "cardScale"
    )

    Box(
        modifier = modifier
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .then(if (tag.isNotEmpty()) Modifier.testTag(tag) else Modifier)
    ) {
        // Bottom 3D lip shadow
        Box(
            modifier = Modifier
                .matchParentSize()
                .padding(top = 4.dp)
                .clip(RoundedCornerShape(cornerRadius))
                .background(lipColor)
        )

        Surface(
            shape = RoundedCornerShape(cornerRadius),
            color = containerColor,
            border = BorderStroke(2.dp, borderColor),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp)
                .clip(RoundedCornerShape(cornerRadius))
                .then(
                    if (onClick != null) {
                        Modifier.clickable(
                            interactionSource = interactionSource,
                            indication = null,
                            onClick = onClick
                        )
                    } else {
                        Modifier
                    }
                )
        ) {
            Box(content = content)
        }
    }
}

/**
 * Thick Duolingo-style rounded progress bar with a bright top highlight sheen.
 */
@Composable
fun StepProgressBar(
    progress: Float,
    modifier: Modifier = Modifier,
    trackColor: Color = MaterialTheme.colorScheme.outline.copy(alpha = 0.45f),
    fillColor: Color = FeatherGreen
) {
    val animatedProgress by animateFloatAsState(
        targetValue = progress.coerceIn(0f, 1f),
        animationSpec = tween(durationMillis = 380, easing = FastOutSlowInEasing),
        label = "progressAnim"
    )

    Box(
        modifier = modifier
            .height(14.dp)
            .clip(RoundedCornerShape(999.dp))
            .background(trackColor)
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(fraction = animatedProgress.coerceAtLeast(0.06f))
                .clip(RoundedCornerShape(999.dp))
                .background(fillColor)
        ) {
            // Subtle top highlight sheen
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .padding(horizontal = 8.dp)
                    .align(Alignment.TopCenter)
                    .offset(y = 2.dp)
                    .clip(RoundedCornerShape(999.dp))
                    .background(Color.White.copy(alpha = 0.35f))
            )
        }
    }
}

/**
 * Animated equalizer bars indicating native audio is currently speaking.
 */
@Composable
fun SoundWaveIndicator(
    isActive: Boolean,
    color: Color,
    modifier: Modifier = Modifier
) {
    val transition = rememberInfiniteTransition(label = "soundwave")
    val bar1 by transition.animateFloat(
        initialValue = 0.35f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(tween(320), RepeatMode.Reverse),
        label = "bar1"
    )
    val bar2 by transition.animateFloat(
        initialValue = 0.9f,
        targetValue = 0.3f,
        animationSpec = infiniteRepeatable(tween(260), RepeatMode.Reverse),
        label = "bar2"
    )
    val bar3 by transition.animateFloat(
        initialValue = 0.4f,
        targetValue = 0.95f,
        animationSpec = infiniteRepeatable(tween(360), RepeatMode.Reverse),
        label = "bar3"
    )

    val heights = if (isActive) listOf(bar1, bar2, bar3, bar2) else listOf(0.45f, 0.75f, 0.55f, 0.35f)

    Row(
        modifier = modifier.height(18.dp),
        horizontalArrangement = Arrangement.spacedBy(2.5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        heights.forEach { fraction ->
            Box(
                modifier = Modifier
                    .width(3.dp)
                    .height((18f * fraction).dp)
                    .clip(RoundedCornerShape(99.dp))
                    .background(color)
            )
        }
    }
}

@Composable
fun OnboardingHeader(
    step: Int,
    totalSteps: Int,
    onBack: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (onBack != null) {
                Surface(
                    onClick = onBack,
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.surface,
                    border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.outline),
                    modifier = Modifier.minimumInteractiveComponentSize()
                ) {
                    Text(
                        text = "←",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                    )
                }
            }
            StepProgressBar(
                progress = step.toFloat() / totalSteps.toFloat(),
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "$step/$totalSteps",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
