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

val Icons.Lock: ImageVector
	get() {
		if (_lockOutline != null) {
			return _lockOutline!!
		}
		_lockOutline = ImageVector.Builder(
			name = "LockOutline", defaultWidth = 24.dp, defaultHeight = 24.dp,
			viewportWidth = 24f, viewportHeight = 24f
		).apply {
			path(
				fill = SolidColor(Color(0xFF000000)),
				stroke = null
			) {
				// M12,17C10.89,17 10,16.1 10,15C10,13.89 10.89,13 12,13A2,2 0 0,1 14,15A2,2 0 0,1 12,17
				moveTo(12f, 17f)
				curveTo(10.89f, 17f, 10f, 16.1f, 10f, 15f)
				curveTo(10f, 13.89f, 10.89f, 13f, 12f, 13f)
				arcToRelative(2f, 2f, 0f, false, true, 2f, 2f)
				arcToRelative(2f, 2f, 0f, false, true, -2f, 2f)

				// M18,20V10H6V20H18
				moveTo(18f, 20f)
				lineTo(18f, 10f)
				lineTo(6f, 10f)
				lineTo(6f, 20f)
				lineTo(18f, 20f)

				// M18,8A2,2 0 0,1 20,10V20A2,2 0 0,1 18,22H6C4.89,22 4,21.1 4,20V10C4,8.89 4.89,8 6,8H7V6A5,5 0 0,1 12,1A5,5 0 0,1 17,6V8H18
				moveTo(18f, 8f)
				arcToRelative(2f, 2f, 0f, false, true, 2f, 2f)
				verticalLineTo(20f)
				arcToRelative(2f, 2f, 0f, false, true, -2f, 2f)
				horizontalLineTo(6f)
				curveTo(4.89f, 22f, 4f, 21.1f, 4f, 20f)
				verticalLineTo(10f)
				curveTo(4f, 8.89f, 4.89f, 8f, 6f, 8f)
				horizontalLineTo(7f)
				verticalLineTo(6f)
				arcToRelative(5f, 5f, 0f, false, true, 5f, -5f)
				arcToRelative(5f, 5f, 0f, false, true, 5f, 5f)
				verticalLineTo(8f)
				horizontalLineTo(18f)

				// M12,3A3,3 0 0,0 9,6V8H15V6A3,3 0 0,0 12,3Z
				moveTo(12f, 3f)
				arcToRelative(3f, 3f, 0f, false, false, -3f, 3f)
				verticalLineTo(8f)
				horizontalLineTo(15f)
				verticalLineTo(6f)
				arcToRelative(3f, 3f, 0f, false, false, -3f, -3f)
				close()
			}
		}.build()
		return _lockOutline!!
	}

private var _lockOutline: ImageVector? = null

