package stepan.gorokhov.paylance.uikit.images

import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val Icons.Lock: ImageVector
	get() {
		if (_Lock != null) {
			return _Lock!!
		}
		_Lock = ImageVector.Builder(
            name = "Lock",
            defaultWidth = 20.dp,
            defaultHeight = 21.dp,
            viewportWidth = 20f,
            viewportHeight = 21f
        ).apply {
			path(
    			fill = null,
    			fillAlpha = 1.0f,
    			stroke = SolidColor(Color(0xFF171A1F)),
    			strokeAlpha = 1.0f,
    			strokeLineWidth = 1.71429f,
    			strokeLineCap = StrokeCap.Square,
    			strokeLineJoin = StrokeJoin.Miter,
    			strokeLineMiter = 10f,
    			pathFillType = PathFillType.NonZero
			) {
				moveTo(15f, 9.78572f)
				lineTo(5f, 9.78572f)
				curveTo(4.2110f, 9.78570f, 3.57140f, 10.42530f, 3.57140f, 11.21430f)
				lineTo(3.57143f, 16.9286f)
				curveTo(3.57140f, 17.71760f, 4.2110f, 18.35710f, 50f, 18.35710f)
				lineTo(15f, 18.3571f)
				curveTo(15.7890f, 18.35710f, 16.42860f, 17.71760f, 16.42860f, 16.92860f)
				verticalLineTo(11.2143f)
				curveTo(16.42860f, 10.42530f, 15.7890f, 9.78570f, 150f, 9.78570f)
				close()
			}
			path(
    			fill = null,
    			fillAlpha = 1.0f,
    			stroke = SolidColor(Color(0xFF171A1F)),
    			strokeAlpha = 1.0f,
    			strokeLineWidth = 1.71429f,
    			strokeLineCap = StrokeCap.Square,
    			strokeLineJoin = StrokeJoin.Miter,
    			strokeLineMiter = 10f,
    			pathFillType = PathFillType.NonZero
			) {
				moveTo(10f, 15.5f)
				curveTo(10.7890f, 15.50f, 11.42860f, 14.86040f, 11.42860f, 14.07140f)
				curveTo(11.42860f, 13.28240f, 10.7890f, 12.64290f, 100f, 12.64290f)
				curveTo(9.2110f, 12.64290f, 8.57140f, 13.28240f, 8.57140f, 14.07140f)
				curveTo(8.57140f, 14.86040f, 9.2110f, 15.50f, 100f, 15.50f)
				close()
			}
			path(
    			fill = null,
    			fillAlpha = 1.0f,
    			stroke = SolidColor(Color(0xFF171A1F)),
    			strokeAlpha = 1.0f,
    			strokeLineWidth = 1.71429f,
    			strokeLineCap = StrokeCap.Square,
    			strokeLineJoin = StrokeJoin.Miter,
    			strokeLineMiter = 10f,
    			pathFillType = PathFillType.NonZero
			) {
				moveTo(13.5714f, 6.92857f)
				verticalLineTo(6.21429f)
				curveTo(13.58080f, 5.27660f, 13.21730f, 4.37360f, 12.56090f, 3.70390f)
				curveTo(11.90460f, 3.03410f, 11.00910f, 2.65250f, 10.07140f, 2.64290f)
				horizontalLineTo(10f)
				curveTo(9.06230f, 2.63350f, 8.15930f, 2.9970f, 7.48960f, 3.65340f)
				curveTo(6.81980f, 4.30970f, 6.43820f, 5.20520f, 6.42860f, 6.14290f)
				verticalLineTo(6.92857f)
			}
		}.build()
		return _Lock!!
	}

private var _Lock: ImageVector? = null
